import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
/**
 * @author ricetofu
 * @date 2024/2/23
 * @description 一键更改所有模块的nacos配置的脚本 (如何还原？直接用git工具批量回滚即可！！)
 */
public class NacosConfigScript {

    private static final String PROFILE_ACTIVE = "dev"; // 生效的配置名称，也会修改指定的配置


    private static final boolean CHANGE_DISCOVER = true; // 是否改动服务发现
    // 一般情况下改下面这三个配置即可
    private static final String DISCOVER_SERVER_ADDR = "192.168.11.1:8848"; // 服务发现的地址
    private static final String DISCOVER_NAMESPACE = "aea957b5-a967-49a7-9fda-f133f2837954"; // 服务发现的命名空间
    private static final String DISCOVER_GROUP = "dev"; // 服务发现的组名

    private static final boolean CHANGE_CONFIG = true; // 是否改动config配置
    private static final String CONFIG_SERVER_ADDR = "192.168.11.1:8848"; // 配置服务的地址
    private static final String CONFIG_NAMESPACE = "aea957b5-a967-49a7-9fda-f133f2837954"; // 配置的命名空间
    private static final String CONFIG_GROUP = "dev"; // 配置的组名

    // 脚本 ！ 启动 ！
    public static void main(String[] args) {

        // 递归寻找所有resources文件夹
        ArrayList<File> files = new ArrayList<>();
        getResourceFiles(files, new File("./"));


        // 然后逐个修改里面的文件
        for (File file : files) {

            File bootstrap = new File(file, "bootstrap.yml"); // 主配置文件
            File config = null;

            if (PROFILE_ACTIVE.equals("dev")) {
                config = new File(file, "bootstrap-dev.yml");
            } else {
                config = new File(file, "bootstrap-prod.yml");
            }

            if (!bootstrap.exists() || !config.exists()) {
                System.out.println("WARN: 位于: " + file.getAbsolutePath() + " 的配置文件夹中缺少nacos配置!");
                continue;
            }

            // 开始修改配置，解析yaml （使用序列化工具会丢失文件内的注释，呜呜呜，我TM手搓的）
            // bootstrap.yaml
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(bootstrap.getAbsoluteFile().toPath())));
            ) {
                List<String> context = new ArrayList<>();
                String line = reader.readLine();
                while (line != null) {
                    context.add(line);
                    line = reader.readLine();
                }

                // 找到应该修改的那项配置的位置
                for (int i = 0; i < context.size(); i++) {
                    if (context.get(i).contains("    active")) {
                        if (context.get(i).contains(PROFILE_ACTIVE)) break; // 不用修改

                        // 需要修改
                        StringBuilder sb = new StringBuilder(context.get(i));

                        int j = context.get(i).indexOf(":"); // 找到第一个冒号的位置
                        j += 2; // 第一个有效字段的位置

                        while (sb.length() > j && sb.charAt(j) != ' ') sb.delete(j, j + 1); // 删除之前的字段
                        sb.insert(j, PROFILE_ACTIVE);

                        context.remove(i);
                        context.add(i, sb.toString());
                        break;
                    }
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(bootstrap.getAbsoluteFile()));

                for (int i = 0; i < context.size(); i++) {
                    writer.write(context.get(i));
                    if (i != context.size() - 1) writer.newLine();
                }

                writer.flush();
                writer.close();

            }catch (Exception e) {
                throw new RuntimeException(e);
            }

            // config
            try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(config.getAbsoluteFile().toPath())));
            ) {

                List<String> context = new ArrayList<>();
                String line = reader.readLine();
                while (line != null) {
                    context.add(line);
                    line = reader.readLine();
                }

                for (int i = 0; i < context.size(); i++) {

                    if (CHANGE_DISCOVER) {
                        // 1
                        if (context.get(i).contains("        server-addr:") && context.get(i - 1).contains("      discovery:")) {

                            int j = context.get(i).indexOf(":");
                            j += 2;
                            StringBuilder sb = new StringBuilder(context.get(i));
                            while (sb.length() > j && sb.charAt(j) != ' ') sb.delete(j, j + 1);
                            sb.insert(j, DISCOVER_SERVER_ADDR);

                            context.remove(i);
                            context.add(i, sb.toString());
                        }

                        // 2
                        if (context.get(i).contains("        namespace:") && context.get(i - 2).contains("      discovery:")) {

                            int j = context.get(i).indexOf(":");
                            j += 2;
                            StringBuilder sb = new StringBuilder(context.get(i));
                            while (sb.length() > j && sb.charAt(j) != ' ') sb.delete(j, j + 1);
                            sb.insert(j, DISCOVER_NAMESPACE);

                            context.remove(i);
                            context.add(i, sb.toString());

                        }

                        // 3
                        if (context.get(i).contains("        group:") && context.get(i - 3).contains("      discovery:")) {

                            int j = context.get(i).indexOf(":");
                            j += 2;
                            StringBuilder sb = new StringBuilder(context.get(i));
                            while (sb.length() > j && sb.charAt(j) != ' ') sb.delete(j, j + 1);
                            sb.insert(j, DISCOVER_GROUP);

                            context.remove(i);
                            context.add(i, sb.toString());

                        }
                    }

                    if (CHANGE_CONFIG) {
                        // 4
                        if (context.get(i).contains("        server-addr:") && context.get(i - 1).contains("      config:")) {

                            int j = context.get(i).indexOf(":");
                            j += 2;
                            StringBuilder sb = new StringBuilder(context.get(i));
                            while (sb.length() > j && sb.charAt(j) != ' ') sb.delete(j, j + 1);
                            sb.insert(j, CONFIG_SERVER_ADDR);

                            context.remove(i);
                            context.add(i, sb.toString());
                        }

                        // 5
                        if (context.get(i).contains("        namespace:") && context.get(i - 3).contains("      config:")) {

                            int j = context.get(i).indexOf(":");
                            j += 2;
                            StringBuilder sb = new StringBuilder(context.get(i));
                            while (sb.length() > j && sb.charAt(j) != ' ') sb.delete(j, j + 1);
                            sb.insert(j, CONFIG_NAMESPACE);

                            context.remove(i);
                            context.add(i, sb.toString());

                        }

                        // 6
                        if (context.get(i).contains("        group:") && context.get(i - 4).contains("      config:")) {

                            int j = context.get(i).indexOf(":");
                            j += 2;
                            StringBuilder sb = new StringBuilder(context.get(i));
                            while (sb.length() > j && sb.charAt(j) != ' ') sb.delete(j, j + 1);
                            sb.insert(j, CONFIG_GROUP);

                            context.remove(i);
                            context.add(i, sb.toString());
                        }
                    }

                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(config.getAbsoluteFile()));

                for (int i = 0; i < context.size(); i++) {
                    writer.write(context.get(i));
                    if (i != context.size() - 1) writer.newLine();
                }

                writer.flush();
                writer.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 递归寻找到所有的resource文件夹，并封装成file对象，存入files对象里面
     * @param files 用于存储File对象的容器
     * @param start 起始的文件夹对象
     * */
    public static void getResourceFiles(List<File> files, File start) {

        File[] listFiles = start.listFiles();

        for (File listFile : listFiles) {

            if (!listFile.isDirectory()) continue; // 不是目录，则直接跳过这个文件

            if (listFile.getName().equals("target")) continue; // 这个目录里面的东西不修改

            if (listFile.getName().equals("resources")) files.add(listFile); // 找到啦 !!!!

            else getResourceFiles(files, listFile); // 继续递归寻找

        }

    }

}
