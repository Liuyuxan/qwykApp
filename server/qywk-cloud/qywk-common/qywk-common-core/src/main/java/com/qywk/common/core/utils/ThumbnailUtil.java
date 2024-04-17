package com.qywk.common.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.stream.StreamSupport;

/**
 * @author qlh
 * @date 2024/2/22 0022 11:32
 * @description 图片压缩工具
 */
public class ThumbnailUtil {
    public static final String COMPRESSION = "compression_";

    // 压缩比率, 低(原质量*0.85),中(原质量*0.7),高(原质量*0.6)
    public static String[] ratios = new String[]{"low", "medium", "high"};
    // 原始格式
    public static String orgForm = "orgForm";

    public static Builder<File> of(File... files) {
        Iterable<File> iter = Arrays.asList(files);
        return new Builder<>(iter);
    }

    public static Builder<BufferedImage> of(BufferedImage... images) {
        return new Builder<>(Arrays.asList(images));
    }

    public static Builder<InputStream> of(InputStream... inputStreams) {
        return new Builder<>(Arrays.asList(inputStreams));
    }

    public static Builder<MultipartFile> of(MultipartFile... multipartFiles) {
        return new Builder<>(Arrays.asList(multipartFiles));
    }

    public static FilenameFilter readFilter() {
        String readFormats[] = ImageIO.getReaderFormatNames();
        Set<String> readFormatSet = new HashSet<>(Arrays.asList(readFormats));
        String writeFormats[] = ImageIO.getWriterFormatNames();
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String seprator = ".";
                if (name == null || !name.contains(seprator)) {
                    return false;
                }
                String format = name.substring(name.lastIndexOf(seprator) + 1);
                return readFormatSet.contains(format);
            }
        };
    }

    public static class Builder<T> {
        // 待转换源数据
        private final Iterable<T> sources;
        // 输出格式
        private String outputFormat = null;
        //        // 原图宽
//        private int width = -1;
//        // 原图高
//        private int height = -1;
        // 压缩比率
        private String compressionRatio = null;

        // 缩放后宽
        private double scaleWidth = Double.NaN;
        // 缩放后高
        private double scaleHeight = Double.NaN;
        // 压缩质量系数 0-1之间
        private double outputQuality = Double.NaN;

        private Builder() {
            sources = null;
        }

        private Builder(Iterable<T> sources) {
            this.sources = sources;
        }

        public Builder<T> identifyCompress(String compressionRatio) {
            if (!Objects.equals(Double.NaN, scaleWidth)
                    || !Objects.equals(Double.NaN, scaleHeight)
                    || !Objects.equals(Double.NaN, outputQuality)
            ) {
                // 有设置scale和outputQuality则不使用自动压缩选项
                return this;
            } else if (null == compressionRatio) {
                this.compressionRatio = ratios[1];
                return this;
            }
            if (!Arrays.toString(ratios).contains(compressionRatio)) {
                throw new IllegalArgumentException("Unsupported compressionRatio Type.");
            }
            this.compressionRatio = compressionRatio;
            return this;
        }

        private Builder<T> identifyCompress(String compressionRatio, int width, int height) {
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Width (" + width + ") and height (" + height + ") cannot be <= 0");
            }
            // 为了支持多线程压缩, 需要将可变变量直接传入方法中,不能使用共享变量返回scaleWidth和outputQuality
            if (!Objects.equals(Double.NaN, scaleWidth)
                    || !Objects.equals(Double.NaN, scaleHeight)
                    || !Objects.equals(Double.NaN, outputQuality)
            ) {
                // 有设置scale和outputQuality则不使用自动压缩选项
                return this;
            } else if (null == compressionRatio) {
                compressionRatio = ratios[1];
            }
            if (!Arrays.toString(ratios).contains(compressionRatio)) {
                throw new IllegalArgumentException("Unsupported compressionRatio Type.");
            }
            int min = width < height ? width : height;
            double offset;
            Builder builder = new Builder();
            if (Objects.equals(ratios[0], compressionRatio)) {
                // 最低压缩,图片保持原来尺寸,质量为原来的0.8
                builder.scaleWidth = builder.scaleHeight = 1.0D;
                builder.outputQuality = 0.8D;
                return builder;
            } else if (Objects.equals(ratios[1], compressionRatio)) {
                offset = 0.4D;
            } else {
                offset = 0.3D;
            }
            if (min <= 1024) {
                // 最小像素小于1024,长和宽不压缩
                builder.scaleWidth = builder.scaleHeight = 1.0D;
                builder.outputQuality = (builder.outputQuality = 0.3D + offset) <= 1 ? builder.outputQuality : 1;
            } else if (min > 1024 && min <= 3 * 1024) {
                builder.scaleHeight = (builder.scaleHeight = 0.4D + offset) <= 1 ? builder.scaleHeight : 1;
                builder.scaleWidth = builder.scaleHeight;
                builder.outputQuality = (builder.outputQuality = 0.3D + offset) <= 1 ? builder.outputQuality : 1;
            } else {
                builder.scaleHeight = (builder.scaleHeight = 2048D / min + offset) <= 1 ? builder.scaleHeight : 1;
                builder.scaleWidth = builder.scaleHeight;
                builder.outputQuality = builder.scaleHeight;
            }
            return builder;
        }

        public Builder<T> scale(double scaleWidth, double scaleHeight) {
            if (scaleWidth <= 0.0 || scaleHeight <= 0.0) {
                throw new IllegalArgumentException(
                        "The scaling factor is equal to or less than 0."
                );
            }
            if (Double.isNaN(scaleWidth) || Double.isNaN(scaleHeight)) {
                throw new IllegalArgumentException(
                        "The scaling factor is not a number."
                );
            }
            if (Double.isInfinite(scaleWidth) || Double.isInfinite(scaleHeight)) {
                throw new IllegalArgumentException(
                        "The scaling factor cannot be infinity."
                );
            }
            this.scaleWidth = scaleWidth;
            this.scaleHeight = scaleHeight;
            return this;
        }

        public Builder<T> scale(double scale) {
            return scale(scale, scale);
        }

        public Builder<T> outputQuality(double quality) {
            if (quality < 0.0f || quality > 1.0f) {
                throw new IllegalArgumentException(
                        "The quality setting must be in the range 0.0f and " +
                                "1.0f, inclusive."
                );
            }
            outputQuality = quality;
            return this;
        }

        public Builder<T> outputFormat(String formatName) {
            if (StringUtils.isEmpty(formatName)) {
                this.outputFormat = orgForm;
                return this;
            } else if (Objects.equals(orgForm, formatName)) {
                this.outputFormat = formatName;
                return this;
            }
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(formatName);
            if (!writers.hasNext()) {
                throw new UnsupportedOperationException(
                        "No suitable ImageWriter found for " + formatName + "."
                );
            }
            this.outputFormat = formatName;
            return this;
        }

        private String outputFormat(T source, String formatName) throws IOException {
            if (source == null) {
                throw new IllegalArgumentException("The resource being processed is null.");
            }
            if (StringUtils.isEmpty(formatName)) {
                formatName = orgForm;
            } else if (!Objects.equals(orgForm, formatName)) {
                return formatName;
            }
            Iterator<ImageReader> iterReader = ImageIO.getImageReaders(ImageIO.createImageInputStream(source));
            if (null == iterReader || !iterReader.hasNext()) {
                throw new UnsupportedOperationException("The resource being processed is not a picture.");
            }
            formatName = iterReader.next().getFormatName();
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(formatName);
            if (!writers.hasNext()) {
                throw new UnsupportedOperationException(
                        "No suitable ImageWriter found for " + formatName + "."
                );
            }
            return formatName;
        }

        private void write(T source, final ImageOutputStream outputStream) throws IOException {
            if (StringUtils.isEmpty(outputFormat)) {
                throw new IllegalStateException("Output format has not been set.");
            }
            Objects.requireNonNull(outputStream, "Could not open OutputStream.");
            BufferedImage srcImage;
            if (source instanceof BufferedImage) {
                srcImage = (BufferedImage) source;
            } else if (source instanceof File) {
                srcImage = ImageIO.read((File) source);
            } else if (source instanceof MultipartFile) {
                srcImage = ImageIO.read(((MultipartFile) source).getInputStream());
                // 将MultipartFile装换为InputStream
                source = (T) ((MultipartFile) source).getInputStream();
            } else if (source instanceof InputStream) {
                srcImage = ImageIO.read((InputStream) source);
            } else {
                throw new IllegalArgumentException("Unsupported ImageIO Type.");
            }
            String outputFormatName = this.outputFormat(source, outputFormat);
//            System.out.println("outputFormatName : " + outputFormatName);
            // 原图宽
            int width = srcImage.getWidth();
            // 原图高
            int height = srcImage.getHeight();
            // 如果没有设置宽高和压缩比,则自动识别最佳压缩比
            Builder builder = this.identifyCompress(compressionRatio, width, height);
            double scaleWidth = builder.scaleWidth;
            double scaleHeight = builder.scaleHeight;
            double outputQuality = builder.outputQuality;
//            System.out.println("scaleWidth ; " + scaleWidth + " scaleHeight : " + scaleHeight + " outputQuality : " + outputQuality);
            if (Objects.equals(outputQuality, Double.NaN)) {
                throw new IllegalArgumentException("outputQuality is null.");
            }
            // 缩放后宽
            int sclWidth = Objects.equals(Double.NaN, scaleWidth) ? width : (int) (width * scaleWidth);
            // 缩放后高
            int sclHeight = Objects.equals(Double.NaN, scaleHeight) ? height : (int) (height * scaleHeight);
//            System.out.println("sclWidth : " + sclWidth + " sclHeight : " + sclHeight);
//            Image from = srcImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
            // 输出BufferedImage流
            long startTime = System.currentTimeMillis();
            BufferedImage destImage =
                    new BufferedImage(sclWidth, sclHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = destImage.createGraphics();
            // 消除锯齿
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_DEFAULT);
            g.addRenderingHints(new HashMap<>());
            g.drawImage(srcImage, 0, 0, sclWidth, sclHeight, null);
//            System.out.println("image scale cost time : " + (System.currentTimeMillis() - startTime));
            // 压缩后增加一点点锐化,如不需要的,以下4行代码可以干掉
            // 拉普拉斯边缘锐化
//            startTime = System.currentTimeMillis();
//            BufferedImage imageSharpen = ImageSharpen.lapLaceSharpDeal(destImage);
//            System.out.println("lapLaceSharpDeal cost time : " + (System.currentTimeMillis() - startTime));
//            //设置为透明覆盖
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.2f));
//            //在背景图片上添加锐化的边缘
//            g.drawImage(imageSharpen, 0, 0, imageSharpen.getWidth(), imageSharpen.getHeight(), null);
//            // 释放对象 透明度设置结束
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

            g.dispose();
            ImageWriter writer = null;
            ImageTypeSpecifier type =
                    ImageTypeSpecifier.createFromRenderedImage(destImage);
            // formatName不生效, 所以统一使用jpg
//            Iterator iterIO = ImageIO.getImageWriters(type, outputFormatName);
            Iterator iterIO = ImageIO.getImageWriters(type, "jpg");
            if (iterIO.hasNext()) {
                writer = (ImageWriter) iterIO.next();
            }
            if (writer == null) {
                throw new IllegalArgumentException("ImageWriter is null.");
            }

            IIOImage iioImage = new IIOImage(destImage, null, null);
            ImageWriteParam param = writer.getDefaultWriteParam();
            if (param.canWriteCompressed() && !outputFormatName.equalsIgnoreCase("bmp")) {
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality((float) outputQuality);    //这里可以指定压缩的程度 0-1.0
            } else {
//                param.setCompressionQuality(0.0f);
//                System.out.println("The outputFormat (" + outputFormatName + ") cannot be compressed");
            }

//            ImageOutputStream outputStream = ImageIO.createImageOutputStream(os);

//            if (outputStream == null) {
//                throw new IOException("Could not open OutputStream.");
//            }
            writer.setOutput(outputStream);
            writer.write(null, iioImage, param);
            writer.dispose();
            outputStream.close();
        }

        public ByteArrayInputStream asByteArray() throws IOException {
            Iterator<T> iter = sources.iterator();
            T source = iter.next();
            if (iter.hasNext()) {
                throw new IllegalArgumentException("Cannot create one thumbnail from multiple original images.");
            }
            // 将缓存中的图片按照指定的配置输出到字节数组中
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            write(source, ImageIO.createImageOutputStream(byteArrayOutputStream));
            // 从字节数组中读取图片
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//            InputStream inputStream = new ByteArrayInputStream(byteArrayInputStream);
//            MultipartFile file = new MockMultipartFile(ContentType.APPLICATION_OCTET_STREAM.toString(), byteArrayInputStream);
            return byteArrayInputStream;
        }

        public MultipartFile[] asMultipartFiles() {
            long startTime = System.currentTimeMillis();
            MultipartFile[] multipartFiles = StreamSupport.stream(sources.spliterator(), true).map(source -> {
                if (!(source instanceof File)
                        && (!(source instanceof MultipartFile))
                ) {
                    throw new IllegalStateException("Cannot create thumbnails to files if original images are not from files or multipartFile.");
                }
                String filename = "";
                String mimeType = "";
                if (source instanceof File) {
                    filename = ((File) source).getName();
                    MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
                    mimeType = fileTypeMap.getContentType(filename);
                } else if (source instanceof MultipartFile) {
                    filename = ((MultipartFile) source).getOriginalFilename();
                    mimeType = ((MultipartFile) source).getContentType();
                }
                // 将缓存中的图片按照指定的配置输出到字节数组中
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
//                    System.out.println("Process the image " + filename + " start.");
                    write(source, ImageIO.createImageOutputStream(byteArrayOutputStream));
                } catch (IOException e) {
                    String desc = "Failed to process the image " + filename + " .";
//                    System.out.println(desc);
                    throw new IllegalArgumentException(desc, e);
                }
                // 从字节数组中读取图片
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                String octetStream = "application/octet-stream";
                MultipartFile multipartFile = null;
                try {
                    multipartFile = new MockMultipartFile(octetStream, filename, mimeType, byteArrayInputStream);
                } catch (IOException e) {
                    String desc = "Failed to mockMultipartFile the image " + filename + " .";
//                    System.out.println(desc);
                    throw new IllegalArgumentException(desc, e);
                }
                return multipartFile;
            }).toArray(MultipartFile[]::new);
//            System.out.println("cost : " + (System.currentTimeMillis() - startTime));
            return multipartFiles;
        }


        public void toFile(final File outFile) throws IOException {
            Iterator<T> iter = sources.iterator();
            T source = iter.next();
            if (iter.hasNext()) {
                throw new IllegalArgumentException("Cannot create one thumbnail from multiple original images.");
            }
            write(source, ImageIO.createImageOutputStream(outFile));
        }

        private void toFiles(Iterable<File> iterable) throws IOException {
            Iterator<File> filenameIter = iterable.iterator();
            for (T source : sources) {
                if (!filenameIter.hasNext()) {
                    throw new IndexOutOfBoundsException(
                            "Not enough file names provided by iterator."
                    );
                }
                write(source, ImageIO.createImageOutputStream(filenameIter.next()));
            }
        }

        public void toFiles(File destinationDir, String namePrefix) throws IOException {
            if (destinationDir == null && namePrefix == null) {
                throw new NullPointerException("destinationDir and rename is null.");
            }
            if (destinationDir != null && !destinationDir.isDirectory()) {
                destinationDir.mkdir();
//                throw new IllegalArgumentException("Given destination is not a directory.");
            }

            if (destinationDir != null && !destinationDir.isDirectory()) {
                throw new IllegalArgumentException("Given destination is not a directory.");
            }
            long startTime = System.currentTimeMillis();
            Builder<T> builder = outputFormat(outputFormat);
            StreamSupport.stream(sources.spliterator(), true).forEach(source -> {
                if (!(source instanceof File)) {
                    throw new IllegalStateException("Cannot create thumbnails to files if original images are not from files.");
                }
                File f = (File) source;
                File actualDestDir = destinationDir == null ? f.getParentFile() : destinationDir;
                String name = StringUtils.isEmpty(namePrefix) ? f.getName() : namePrefix + f.getName();
                if (!Objects.equals(orgForm, builder.outputFormat)) {
                    name = name.substring(0, name.lastIndexOf(".")) + "." + outputFormat;
                }
                File destinationFile = new File(actualDestDir, name);
                try {
//                    System.out.println("Process the image " + f.getName() + " start.");
                    write((T) source, ImageIO.createImageOutputStream(destinationFile));
                } catch (Exception e) {
//                    System.out.println("Failed to process the image " + f.getName() + " .");
                    e.printStackTrace();
                }
            });
//            System.out.println("cost : " + (System.currentTimeMillis() - startTime));
        }

        public void toOutputStream(final OutputStream outputStream) throws IOException {
            Iterator<T> iter = sources.iterator();
            T source = iter.next();
            if (iter.hasNext()) {
                throw new IllegalArgumentException("Cannot create one thumbnail from multiple original images.");
            }
            write(source, ImageIO.createImageOutputStream(outputStream));
        }

        public void toOutputStreams(Iterable<? extends OutputStream> iterable) throws IOException {
            Iterator<? extends OutputStream> filenameIter = iterable.iterator();
            for (T source : sources) {
                if (!filenameIter.hasNext()) {
                    throw new IndexOutOfBoundsException(
                            "Not enough file names provided by iterator."
                    );
                }
                write(source, ImageIO.createImageOutputStream(filenameIter.next()));
            }
        }
    }

    public static File compression(File file, Double percentage){
        if(!(percentage > 0 && percentage <= 1)){
            throw new RuntimeException("百分比值有误");
        }
        String originalFilename = file.getName();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        File target = null;
        try {
            target = File.createTempFile(UUID.randomUUID().toString(), suffix);
            ThumbnailUtil.of(file).
                    identifyCompress(ThumbnailUtil.ratios[0]).
                    scale(1D).outputQuality(percentage).
                    outputFormat(ThumbnailUtil.orgForm).
                    toFile(target);
        } catch (Exception e) {
            target.delete();
            e.printStackTrace();
        }
        return target;
    }

    public static File compression(MultipartFile file, Double percentage){
        if(!(percentage > 0 && percentage <= 1)){
            throw new RuntimeException("百分比值有误");
        }
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        File target = null;
        try {
            target = File.createTempFile(UUID.randomUUID().toString(), suffix);
            ThumbnailUtil.of(file).
                    identifyCompress(ThumbnailUtil.ratios[0]).
                    scale(1D).outputQuality(percentage).
                    outputFormat(ThumbnailUtil.orgForm).
                    toFile(target);
        } catch (Exception e) {
            target.delete();
            e.printStackTrace();
        }
        return target;
    }
}

