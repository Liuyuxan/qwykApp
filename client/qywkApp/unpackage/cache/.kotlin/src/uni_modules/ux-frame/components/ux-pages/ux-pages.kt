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
open class GenUniModulesUxFrameComponentsUxPagesUxPages : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onMounted(fun() {
            this.init();
        }
        , instance);
        this.`$watch`(fun(): Any? {
            return this.index;
        }
        , fun() {
            if (this.index <= this.pageList.length - 1) {
                this.pageList[this.index].init = true;
            }
        }
        );
        this.`$watch`(fun(): Any? {
            return this.pages;
        }
        , fun() {
            this.init();
        }
        );
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("view", utsMapOf("class" to "ux-pages"), utsArrayOf(
            if (isTrue(_ctx.pageList[0].init)) {
                withDirectives(createElementVNode("view", utsMapOf("key" to 0, "class" to "ux-pages__item"), utsArrayOf(
                    renderSlot(_ctx.`$slots`, "0", UTSJSONObject(), fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "ux-pages__error"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "ux-pages__error__text"), "页面未配置")
                            ))
                        );
                    })
                ), 512), utsArrayOf(
                    utsArrayOf(
                        vShow,
                        _ctx.index == 0
                    )
                ));
            } else {
                createCommentVNode("v-if", true);
            }
            ,
            if (isTrue(_ctx.pageList[1].init)) {
                withDirectives(createElementVNode("view", utsMapOf("key" to 1, "class" to "ux-pages__item"), utsArrayOf(
                    renderSlot(_ctx.`$slots`, "1", UTSJSONObject(), fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "ux-pages__error"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "ux-pages__error__text"), "页面未配置")
                            ))
                        );
                    })
                ), 512), utsArrayOf(
                    utsArrayOf(
                        vShow,
                        _ctx.index == 1
                    )
                ));
            } else {
                createCommentVNode("v-if", true);
            }
            ,
            if (isTrue(_ctx.pageList[2].init)) {
                withDirectives(createElementVNode("view", utsMapOf("key" to 2, "class" to "ux-pages__item"), utsArrayOf(
                    renderSlot(_ctx.`$slots`, "2", UTSJSONObject(), fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "ux-pages__error"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "ux-pages__error__text"), "页面未配置")
                            ))
                        );
                    })
                ), 512), utsArrayOf(
                    utsArrayOf(
                        vShow,
                        _ctx.index == 2
                    )
                ));
            } else {
                createCommentVNode("v-if", true);
            }
            ,
            if (isTrue(_ctx.pageList[3].init)) {
                withDirectives(createElementVNode("view", utsMapOf("key" to 3, "class" to "ux-pages__item"), utsArrayOf(
                    renderSlot(_ctx.`$slots`, "3", UTSJSONObject(), fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "ux-pages__error"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "ux-pages__error__text"), "页面未配置")
                            ))
                        );
                    })
                ), 512), utsArrayOf(
                    utsArrayOf(
                        vShow,
                        _ctx.index == 3
                    )
                ));
            } else {
                createCommentVNode("v-if", true);
            }
            ,
            if (isTrue(_ctx.pageList[4].init)) {
                withDirectives(createElementVNode("view", utsMapOf("key" to 4, "class" to "ux-pages__item"), utsArrayOf(
                    renderSlot(_ctx.`$slots`, "4", UTSJSONObject(), fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "ux-pages__error"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "ux-pages__error__text"), "页面未配置")
                            ))
                        );
                    })
                ), 512), utsArrayOf(
                    utsArrayOf(
                        vShow,
                        _ctx.index == 4
                    )
                ));
            } else {
                createCommentVNode("v-if", true);
            }
        ));
    }
    open var index: Number by `$props`;
    open var pages: Number by `$props`;
    open var pageList: UTSArray<Page> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("pageList" to utsArrayOf<Page>(Page(index = 0, init = true), Page(index = 1, init = false), Page(index = 2, init = false), Page(index = 3, init = false), Page(index = 4, init = false)));
    }
    override fun `$initMethods`() {
        this.init = fun() {
            if (this.pages > 5) {
                console.warn("[ux-pages]配置警告: 页面最大支持5页", " at uni_modules/ux-frame/components/ux-pages/index.uts:55");
            }
        }
        ;
    }
    open lateinit var init: () -> Unit;
    companion object {
        var name = "ux-pages";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("ux-pages" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%")), "ux-pages__item" to padStyleMapOf(utsMapOf("position" to "absolute", "top" to 0, "right" to 0, "bottom" to 0, "left" to 0)), "ux-pages__error" to padStyleMapOf(utsMapOf("position" to "absolute", "top" to 0, "right" to 0, "bottom" to 0, "left" to 0, "display" to "flex", "flexDirection" to "column", "justifyContent" to "center", "alignItems" to "center")), "ux-pages__error__text" to padStyleMapOf(utsMapOf("fontSize" to 15, "color" to "#808080")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf("index" to utsMapOf("type" to "Number", "default" to 0), "pages" to utsMapOf("type" to "Number", "default" to 0)));
        var propsNeedCastKeys = utsArrayOf(
            "index",
            "pages"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
