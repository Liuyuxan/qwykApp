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
import io.dcloud.uniapp.extapi.reLaunch as uni_reLaunch;
open class GenComponentsTabbarTabbar : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_ux_tabbar = resolveEasyComponent("ux-tabbar", GenUniModulesUxFrameComponentsUxTabbarUxTabbarClass);
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_ux_tabbar, utsMapOf("type" to "special", "index" to 0, "anim" to "scroll", "iconSize" to 30, "selectedColor" to "#965D30", "unselectedColor" to "#808080", "data" to _ctx.tabs, "corner" to 20, "border" to false, "onChange" to _ctx.tabChange), utsMapOf("center" to withSlotCtx(fun(): UTSArray<Any> {
                return utsArrayOf(
                    createElementVNode("image", utsMapOf("onTouchstart" to _ctx.handlerTouchstart, "onTouchmove" to _ctx.handlerTouchmove, "onTouchend" to _ctx.handlerTouchend, "class" to "center-img", "src" to "/static/image/tabBar/选择3.png", "mode" to "widthFix"), null, 40, utsArrayOf(
                        "onTouchstart",
                        "onTouchmove",
                        "onTouchend"
                    ))
                );
            }
            ), "_" to 1), 8, utsArrayOf(
                "data",
                "onChange"
            ))
        ), 4);
    }
    open var currentIndex: Any? by `$props`;
    open var tabIndex: Number by `$data`;
    open var direction: String by `$data`;
    open var loop: Number by `$data`;
    open var clickShow: Boolean by `$data`;
    open var longClickShow: Boolean by `$data`;
    open var tabs: UTSArray<UxTab> by `$data`;
    open var pageUrls: UTSArray<String> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("tabIndex" to 0 as Number, "direction" to "right", "loop" to 0, "clickShow" to false, "longClickShow" to false, "tabs" to utsArrayOf<UxTab>(UxTab(name = "展架", selectedIcon = "/static/image/tabBar/未选择1.png", unselectedIcon = "/static/image/tabBar/选择1.png"), UxTab(name = "集市", selectedIcon = "/static/image/tabBar/未选择2.png", unselectedIcon = "/static/image/tabBar/选择2.png"), UxTab(name = "膳食", selectedIcon = "/static/image/tabBar/未选择4.png", unselectedIcon = "/static/image/tabBar/选择4.png"), UxTab(name = "我的", selectedIcon = "/static/image/tabBar/未选择5.png", unselectedIcon = "/static/image/tabBar/选择5.png")), "pageUrls" to utsArrayOf(
            "/pages/showWindow/showWindow",
            "/pages/community/community",
            "/pages/health/health",
            "/pages/myPage/myPage"
        ));
    }
    override fun `$initMethods`() {
        this.handlerTouchstart = fun() {
            this.loop = setTimeout(fun(){
                this.loop = 0;
                console.log("长按", " at components/tabbar/tabbar.uvue:68");
                this.clickShow = false;
                this.longClickShow = true;
            }
            , 500);
        }
        ;
        this.handlerTouchmove = fun() {
            clearTimeout(this.loop);
            this.loop = 0;
        }
        ;
        this.handlerTouchend = fun() {
            clearTimeout(this.loop);
            if (this.loop !== 0) {
                console.log("单击", " at components/tabbar/tabbar.uvue:85");
                if (this.currentIndex === 2) {
                    return;
                }
                uni_reLaunch(ReLaunchOptions(url = "/pages/index/index"));
                this.clickShow = true;
                this.longClickShow = false;
            }
        }
        ;
        this.tabChange = fun(index: Number) {
            this.tabIndex = index;
            var pageUrl = this.pageUrls[index];
            uni_reLaunch(ReLaunchOptions(url = pageUrl));
        }
        ;
    }
    open lateinit var handlerTouchstart: () -> Unit;
    open lateinit var handlerTouchmove: () -> Unit;
    open lateinit var handlerTouchend: () -> Unit;
    open lateinit var tabChange: (index: Number) -> Unit;
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("center-img" to padStyleMapOf(utsMapOf("width" to 40)));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf("currentIndex" to utsMapOf("default" to 2)));
        var propsNeedCastKeys = utsArrayOf(
            "currentIndex"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
