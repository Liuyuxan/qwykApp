@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME")
package uni.UNIB7338A2;
import io.dcloud.uniapp.*;
import io.dcloud.uniapp.extapi.*;
import io.dcloud.uniapp.framework.*;
import io.dcloud.uniapp.runtime.*;
import io.dcloud.uniapp.vue.*;
import io.dcloud.uniapp.vue.shared.*;
import io.dcloud.unicloud.*;
import io.dcloud.uts.*;
import io.dcloud.uts.Map;
import io.dcloud.uts.Set;
import io.dcloud.uts.UTSAndroid;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.async;
open class GenUniModulesUxFrameComponentsUxTextUxText : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onMounted(fun() {}, instance);
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_ux_icon = resolveEasyComponent("ux-icon", GenUniModulesUxFrameComponentsUxIconUxIconClass);
        return createElementVNode("text", utsMapOf("class" to "ux-text", "selectable" to _ctx.selectable, "space" to _ctx.space, "decode" to _ctx.decode, "style" to normalizeStyle(_ctx.style), "onClick" to _ctx.click), utsArrayOf(
            toDisplayString(_ctx.value) + " ",
            if (_ctx.suffixIcon != "") {
                createVNode(_component_ux_icon, utsMapOf("key" to 0, "type" to _ctx.suffixIcon, "size" to _ctx.iconSize, "color" to _ctx._color, "custom-font" to _ctx.customFont, "custom-family" to _ctx.customFamily), null, 8, utsArrayOf(
                    "type",
                    "size",
                    "color",
                    "custom-font",
                    "custom-family"
                ));
            } else {
                createCommentVNode("v-if", true);
            }
        ), 12, utsArrayOf(
            "selectable",
            "space",
            "decode",
            "onClick"
        ));
    }
    open var margin: UTSArray<Any> by `$props`;
    open var mt: Number by `$props`;
    open var mr: Number by `$props`;
    open var mb: Number by `$props`;
    open var ml: Number by `$props`;
    open var padding: UTSArray<Any> by `$props`;
    open var pt: Number by `$props`;
    open var pr: Number by `$props`;
    open var pb: Number by `$props`;
    open var pl: Number by `$props`;
    open var xstyle: UTSArray<Any> by `$props`;
    open var disabled: Boolean by `$props`;
    open var theme: String by `$props`;
    open var text: String by `$props`;
    open var color: String by `$props`;
    open var size: Number by `$props`;
    open var bold: Boolean by `$props`;
    open var lines: Number by `$props`;
    open var align: String by `$props`;
    open var decoration: String by `$props`;
    open var lineHeight: Number by `$props`;
    open var prefixIcon: String by `$props`;
    open var suffixIcon: String by `$props`;
    open var iconSize: Number by `$props`;
    open var customFont: String by `$props`;
    open var customFamily: String by `$props`;
    open var selectable: Boolean by `$props`;
    open var space: String by `$props`;
    open var decode: Boolean by `$props`;
    open var mode: String by `$props`;
    open var format: String by `$props`;
    open var href: String by `$props`;
    open var call: Boolean by `$props`;
    open var _color: String by `$data`;
    open var style: UTSArray<Any> by `$data`;
    open var value: String by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("_color" to computed<String>(fun(): String {
            if (this.color != "") {
                return this.color;
            }
            when (this.theme) {
                "info" -> 
                    {
                        return `$ux`.theme.main!!;
                        return `$ux`.theme.main!!;
                    }
                "primary" -> 
                    {
                        return `$ux`.theme.primary!!;
                        return `$ux`.theme.main!!;
                    }
                "warn" -> 
                    {
                        return `$ux`.theme.warn!!;
                        return `$ux`.theme.main!!;
                    }
                "success" -> 
                    {
                        return `$ux`.theme.success!!;
                        return `$ux`.theme.main!!;
                    }
                "error" -> 
                    {
                        return `$ux`.theme.error!!;
                        return `$ux`.theme.main!!;
                    }
                "title" -> 
                    {
                        return `$ux`.theme.title!!;
                        return `$ux`.theme.main!!;
                    }
                else -> 
                    return `$ux`.theme.main!!;
            }
        }
        ), "style" to computed<UTSArray<Any>>(fun(): UTSArray<Any> {
            var css = Map<String, Any?>();
            css.set("font-size", `$ux`.Util.addUnit(this.size));
            css.set("color", if (this.mode == "link") {
                "#023aff";
            } else {
                this._color;
            }
            );
            css.set("font-weight", if (this.bold) {
                "bold";
            } else {
                "normal";
            }
            );
            css.set("text-align", this.align);
            if (this.mode == "link") {
                css.set("text-decoration-line", "underline");
            } else {
                css.set("text-decoration-line", this.decoration);
            }
            if (this.lines > 0) {
                css.set("lines", this.lines);
                css.set("text-overflow", "ellipsis");
            }
            css.set("line-height", this.lineHeight);
            css = `$ux`.Util.xStyle(css, this.margin, this.mt, this.mr, this.mb, this.ml, this.padding, this.pt, this.pr, this.pb, this.pl);
            return utsArrayOf(
                css,
                this.xstyle
            );
        }
        ), "value" to computed<String>(fun(): String {
            var text = this.text;
            if (this.mode == "text") {
                ;
            } else if (this.mode == "phone") {
                if (this.format == "verify" || this.format == "encrypt") {
                    if (!`$ux`.Verify.isPhone(text)) {
                        console.error("[ux-text]\u624B\u673A\u53F7\u9A8C\u8BC1\u5931\u8D25", " at uni_modules/ux-frame/components/ux-text/index.uts:174");
                    }
                }
                if (this.format == "encrypt") {
                    text = "" + text.substring(0, 3) + "****" + text.substring(7);
                }
            } else if (this.mode == "name") {
                if (this.format == "verify" || this.format == "encrypt") {
                    if (text.length <= 1) {
                        console.error("[ux-text]\u59D3\u540D\u9A8C\u8BC1\u5931\u8D25", " at uni_modules/ux-frame/components/ux-text/index.uts:186");
                    }
                }
                if (this.format == "encrypt") {
                    text = `$ux`.Fmt.encryptText(text);
                }
            } else if (this.mode == "date") {
                if (this.format != "") {
                    if (!`$ux`.Verify.isDate(text)) {
                        console.error("[ux-text]\u65E5\u671F\u9A8C\u8BC1\u5931\u8D25", " at uni_modules/ux-frame/components/ux-text/index.uts:198");
                    }
                    text = `$ux`.Date.fmtDate(text, this.format);
                }
            } else if (this.mode == "link") {
                if (this.format == "verify") {
                    if (!`$ux`.Verify.isURL(text)) {
                        console.error("[ux-text]\u8D85\u94FE\u63A5\u9A8C\u8BC1\u5931\u8D25", " at uni_modules/ux-frame/components/ux-text/index.uts:208");
                    }
                }
            } else if (this.mode == "money") {
                if (this.format == "verify") {
                    if (!UTSRegExp("^\\d+(\\.\\d+)?\$", "").test(text)) {
                        console.error("[ux-text]\u91D1\u989D\u9A8C\u8BC1\u5931\u8D25", " at uni_modules/ux-frame/components/ux-text/index.uts:215");
                    }
                }
                if (this.format == "cmoney") {
                    text = `$ux`.Fmt.upperMoney(text);
                } else if (this.format == "qmoney") {
                    text = `$ux`.Fmt.fmtMoney(text, false);
                } else if (this.format == "wmoney") {
                    text = `$ux`.Fmt.fmtMoney(text, true);
                }
            }
            return text;
        }
        ));
    }
    override fun `$initMethods`() {
        this.click = fun(e: MouseEvent) {
            if (this.call && this.mode == "phone") {
                ;
            }
            if (this.mode == "link") {
                ;
            }
            this.`$emit`("click", e);
        }
        ;
    }
    open lateinit var click: (e: MouseEvent) -> Unit;
    companion object {
        var name = "ux-text";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf());
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf("click" to null);
        var props = normalizePropsOptions(utsMapOf("margin" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf();
        }
        ), "mt" to utsMapOf("type" to "Number", "default" to 0), "mr" to utsMapOf("type" to "Number", "default" to 0), "mb" to utsMapOf("type" to "Number", "default" to 0), "ml" to utsMapOf("type" to "Number", "default" to 0), "padding" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf();
        }
        ), "pt" to utsMapOf("type" to "Number", "default" to 0), "pr" to utsMapOf("type" to "Number", "default" to 0), "pb" to utsMapOf("type" to "Number", "default" to 0), "pl" to utsMapOf("type" to "Number", "default" to 0), "xstyle" to utsMapOf("type" to "Array", "default" to fun(): UTSArray<Any> {
            return utsArrayOf<Any>();
        }
        ), "disabled" to utsMapOf("type" to "Boolean", "default" to false), "theme" to utsMapOf("type" to "String", "default" to "info"), "text" to utsMapOf("type" to "String", "default" to ""), "color" to utsMapOf("type" to "String", "default" to ""), "size" to utsMapOf("type" to "Number", "default" to 14), "bold" to utsMapOf("type" to "Boolean", "default" to false), "lines" to utsMapOf("type" to "Number", "default" to 0), "align" to utsMapOf("type" to "String", "default" to "left"), "decoration" to utsMapOf("type" to "String", "default" to "none"), "lineHeight" to utsMapOf("type" to "Number", "default" to 1.2), "prefixIcon" to utsMapOf("type" to "String", "default" to ""), "suffixIcon" to utsMapOf("type" to "String", "default" to ""), "iconSize" to utsMapOf("type" to "Number", "default" to 14), "customFont" to utsMapOf("type" to "String", "default" to ""), "customFamily" to utsMapOf("type" to "String", "default" to ""), "selectable" to utsMapOf("type" to "Boolean", "default" to false), "space" to utsMapOf("type" to "String", "default" to ""), "decode" to utsMapOf("type" to "Boolean", "default" to false), "mode" to utsMapOf("type" to "String", "default" to "text"), "format" to utsMapOf("type" to "String", "default" to ""), "href" to utsMapOf("type" to "String", "default" to ""), "call" to utsMapOf("type" to "Boolean", "default" to false)));
        var propsNeedCastKeys = utsArrayOf(
            "margin",
            "mt",
            "mr",
            "mb",
            "ml",
            "padding",
            "pt",
            "pr",
            "pb",
            "pl",
            "xstyle",
            "disabled",
            "theme",
            "text",
            "color",
            "size",
            "bold",
            "lines",
            "align",
            "decoration",
            "lineHeight",
            "prefixIcon",
            "suffixIcon",
            "iconSize",
            "customFont",
            "customFamily",
            "selectable",
            "space",
            "decode",
            "mode",
            "format",
            "href",
            "call"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
