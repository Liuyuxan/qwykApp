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
import io.dcloud.uniapp.appframe.AppConfig;
import io.dcloud.uniapp.extapi.clearStorageSync as uni_clearStorageSync;
import io.dcloud.uniapp.extapi.connectSocket as uni_connectSocket;
import io.dcloud.uniapp.extapi.exit as uni_exit;
import io.dcloud.uniapp.extapi.getStorageSync as uni_getStorageSync;
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync;
import io.dcloud.uniapp.extapi.reLaunch as uni_reLaunch;
import io.dcloud.uniapp.extapi.removeInterceptor as uni_removeInterceptor;
import io.dcloud.uniapp.extapi.showToast as uni_showToast;
val BASE_URL = "http://192.168.252.249:9000";
open class Utils {
    companion object {
        fun checkLogin() {
            val token = uni_getStorageSync("token") as String;
            console.log("token", token, " at utils/utils.uts:9");
            if (token.length <= 0) {
                uni_showToast(ShowToastOptions(title = "您暂未登录，请登录"));
                uni_reLaunch(ReLaunchOptions(url = "/pages/login/login"));
                uni_clearStorageSync();
            }
        }
        fun getBaseURL(url: String): String {
            return BASE_URL + url;
        }
    }
}
var firstBackTime: Number = 0;
open class GenApp : BaseApp {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onLaunch(fun(_: OnLaunchOptions) {
            console.log("App Launch", " at App.uvue:7");
        }
        , instance);
        onAppShow(fun(_: OnShowOptions) {
            Utils.checkLogin();
            console.log("App Show", " at App.uvue:11");
        }
        , instance);
        onHide(fun() {
            console.log("App Hide", " at App.uvue:14");
        }
        , instance);
        onLastPageBackPress(fun() {
            console.log("App LastPageBackPress", " at App.uvue:18");
            if (firstBackTime == 0) {
                uni_showToast(ShowToastOptions(title = "再按一次退出应用", position = "bottom"));
                firstBackTime = Date.now();
                setTimeout(fun(){
                    firstBackTime = 0;
                }, 2000);
            } else if (Date.now() - firstBackTime < 2000) {
                firstBackTime = Date.now();
                uni_exit(null);
            }
        }
        , instance);
        onExit(fun() {
            uni_removeInterceptor("navigateTo", null);
            console.log("App Exit", " at App.uvue:36");
        }
        , instance);
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0,
                    styles1,
                    styles2
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("iconfont" to padStyleMapOf(utsMapOf("fontFamily" to "iconfont")), "view" to padStyleMapOf(utsMapOf("fontSize" to "28rpx", "lineHeight" to 1.8, "color" to "#0E151D")), "text" to padStyleMapOf(utsMapOf("fontSize" to "28rpx", "lineHeight" to 1.8, "color" to "#0E151D")), "center" to padStyleMapOf(utsMapOf("left" to "50%", "transform" to "translateX(-50%)")), "row" to padStyleMapOf(utsMapOf("marginRight" to "-20rpx", "marginLeft" to "-20rpx", "flexWrap" to "wrap", "flexDirection" to "row")), "col-1" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "62.5rpx")), "col-2" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "125rpx")), "col-3" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "187.5rpx")), "col-4" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "250rpx")), "col-5" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "312.5rpx")), "col-6" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "375rpx")), "col-7" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "437.5rpx")), "col-8" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "500rpx")), "col-9" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "562.5rpx")), "col-10" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "625rpx")), "col-11" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "687.5rpx")), "col-12" to padStyleMapOf(utsMapOf("position" to "relative", "paddingRight" to "20rpx", "paddingLeft" to "20rpx", "width" to "750rpx")), "col-offset-12" to padStyleMapOf(utsMapOf("marginLeft" to "750rpx")), "col-offset-11" to padStyleMapOf(utsMapOf("marginLeft" to "687.5rpx")), "col-offset-10" to padStyleMapOf(utsMapOf("marginLeft" to "625rpx")), "col-offset-9" to padStyleMapOf(utsMapOf("marginLeft" to "562.5rpx")), "col-offset-8" to padStyleMapOf(utsMapOf("marginLeft" to "500rpx")), "col-offset-7" to padStyleMapOf(utsMapOf("marginLeft" to "437.5rpx")), "col-offset-6" to padStyleMapOf(utsMapOf("marginLeft" to "375rpx")), "col-offset-5" to padStyleMapOf(utsMapOf("marginLeft" to "312.5rpx")), "col-offset-4" to padStyleMapOf(utsMapOf("marginLeft" to "250rpx")), "col-offset-3" to padStyleMapOf(utsMapOf("marginLeft" to "187.5rpx")), "col-offset-2" to padStyleMapOf(utsMapOf("marginLeft" to "125rpx")), "col-offset-1" to padStyleMapOf(utsMapOf("marginLeft" to "62.5rpx")), "col-offset-0" to padStyleMapOf(utsMapOf("marginLeft" to 0)), "flex" to padStyleMapOf(utsMapOf("!flexDirection" to "row")), "flex-row" to padStyleMapOf(utsMapOf("!flexDirection" to "row")), "flex-column" to padStyleMapOf(utsMapOf("!flexDirection" to "column")), "flex-row-reverse" to padStyleMapOf(utsMapOf("!flexDirection" to "row-reverse")), "flex-column-reverse" to padStyleMapOf(utsMapOf("!flexDirection" to "column-reverse")), "flex-wrap" to padStyleMapOf(utsMapOf("flexWrap" to "wrap")), "flex-nowrap" to padStyleMapOf(utsMapOf("flexWrap" to "nowrap")), "justify-start" to padStyleMapOf(utsMapOf("justifyContent" to "flex-start")), "justify-end" to padStyleMapOf(utsMapOf("justifyContent" to "flex-end")), "justify-between" to padStyleMapOf(utsMapOf("justifyContent" to "space-between")), "justify-around" to padStyleMapOf(utsMapOf("justifyContent" to "space-around")), "justify-center" to padStyleMapOf(utsMapOf("justifyContent" to "center")), "align-center" to padStyleMapOf(utsMapOf("!alignItems" to "center")), "align-stretch" to padStyleMapOf(utsMapOf("alignItems" to "stretch")), "align-start" to padStyleMapOf(utsMapOf("alignItems" to "flex-start")), "align-end" to padStyleMapOf(utsMapOf("alignItems" to "flex-end")), "flex-1" to padStyleMapOf(utsMapOf("!flex" to 1)), "flex-2" to padStyleMapOf(utsMapOf("flex" to 2)), "flex-3" to padStyleMapOf(utsMapOf("flex" to 3)), "flex-4" to padStyleMapOf(utsMapOf("flex" to 4)), "flex-5" to padStyleMapOf(utsMapOf("flex" to 5)), "container" to padStyleMapOf(utsMapOf("paddingRight" to "20rpx", "paddingLeft" to "20rpx")), "m-0" to padStyleMapOf(utsMapOf("marginTop" to 0, "marginRight" to 0, "marginBottom" to 0, "marginLeft" to 0)), "m-1" to padStyleMapOf(utsMapOf("marginTop" to "10rpx", "marginRight" to "10rpx", "marginBottom" to "10rpx", "marginLeft" to "10rpx")), "m-2" to padStyleMapOf(utsMapOf("marginTop" to "20rpx", "marginRight" to "20rpx", "marginBottom" to "20rpx", "marginLeft" to "20rpx")), "m-3" to padStyleMapOf(utsMapOf("marginTop" to "30rpx", "marginRight" to "30rpx", "marginBottom" to "30rpx", "marginLeft" to "30rpx")), "m-4" to padStyleMapOf(utsMapOf("marginTop" to "40rpx", "marginRight" to "40rpx", "marginBottom" to "40rpx", "marginLeft" to "40rpx")), "m-5" to padStyleMapOf(utsMapOf("marginTop" to "50rpx", "marginRight" to "50rpx", "marginBottom" to "50rpx", "marginLeft" to "50rpx")), "mt-0" to padStyleMapOf(utsMapOf("marginTop" to 0)), "mt-1" to padStyleMapOf(utsMapOf("marginTop" to "10rpx")), "mt-2" to padStyleMapOf(utsMapOf("marginTop" to "20rpx")), "mt-3" to padStyleMapOf(utsMapOf("marginTop" to "30rpx")), "mt-4" to padStyleMapOf(utsMapOf("marginTop" to "40rpx")), "mt-5" to padStyleMapOf(utsMapOf("marginTop" to "50rpx")), "mb-0" to padStyleMapOf(utsMapOf("marginBottom" to 0)), "mb-1" to padStyleMapOf(utsMapOf("marginBottom" to "10rpx")), "mb-2" to padStyleMapOf(utsMapOf("marginBottom" to "20rpx")), "mb-3" to padStyleMapOf(utsMapOf("marginBottom" to "30rpx")), "mb-4" to padStyleMapOf(utsMapOf("marginBottom" to "40rpx")), "mb-5" to padStyleMapOf(utsMapOf("marginBottom" to "50rpx")), "ml-0" to padStyleMapOf(utsMapOf("marginLeft" to 0)), "ml-1" to padStyleMapOf(utsMapOf("marginLeft" to "10rpx")), "ml-2" to padStyleMapOf(utsMapOf("marginLeft" to "20rpx")), "ml-3" to padStyleMapOf(utsMapOf("marginLeft" to "30rpx")), "ml-4" to padStyleMapOf(utsMapOf("marginLeft" to "40rpx")), "ml-5" to padStyleMapOf(utsMapOf("marginLeft" to "50rpx")), "mr-0" to padStyleMapOf(utsMapOf("marginRight" to 0)), "mr-1" to padStyleMapOf(utsMapOf("marginRight" to "10rpx")), "mr-2" to padStyleMapOf(utsMapOf("marginRight" to "20rpx")), "mr-3" to padStyleMapOf(utsMapOf("marginRight" to "30rpx")), "mr-4" to padStyleMapOf(utsMapOf("marginRight" to "40rpx")), "mr-5" to padStyleMapOf(utsMapOf("marginRight" to "50rpx")), "my-0" to padStyleMapOf(utsMapOf("marginTop" to 0, "marginBottom" to 0)), "my-1" to padStyleMapOf(utsMapOf("marginTop" to "10rpx", "marginBottom" to "10rpx")), "my-2" to padStyleMapOf(utsMapOf("marginTop" to "20rpx", "marginBottom" to "20rpx")), "my-3" to padStyleMapOf(utsMapOf("marginTop" to "30rpx", "marginBottom" to "30rpx")), "my-4" to padStyleMapOf(utsMapOf("marginTop" to "40rpx", "marginBottom" to "40rpx")), "my-5" to padStyleMapOf(utsMapOf("marginTop" to "50rpx", "marginBottom" to "50rpx")), "mx-0" to padStyleMapOf(utsMapOf("marginLeft" to 0, "marginRight" to 0)), "mx-1" to padStyleMapOf(utsMapOf("marginLeft" to "10rpx", "marginRight" to "10rpx")), "mx-2" to padStyleMapOf(utsMapOf("marginLeft" to "20rpx", "marginRight" to "20rpx")), "mx-3" to padStyleMapOf(utsMapOf("marginLeft" to "30rpx", "marginRight" to "30rpx")), "mx-4" to padStyleMapOf(utsMapOf("marginLeft" to "40rpx", "marginRight" to "40rpx")), "mx-5" to padStyleMapOf(utsMapOf("marginLeft" to "50rpx", "marginRight" to "50rpx")), "p-0" to padStyleMapOf(utsMapOf("paddingTop" to 0, "paddingRight" to 0, "paddingBottom" to 0, "paddingLeft" to 0)), "p" to padStyleMapOf(utsMapOf("paddingTop" to "5rpx", "paddingRight" to "5rpx", "paddingBottom" to "5rpx", "paddingLeft" to "5rpx")), "p-1" to padStyleMapOf(utsMapOf("paddingTop" to "10rpx", "paddingRight" to "10rpx", "paddingBottom" to "10rpx", "paddingLeft" to "10rpx")), "p-2" to padStyleMapOf(utsMapOf("paddingTop" to "20rpx", "paddingRight" to "20rpx", "paddingBottom" to "20rpx", "paddingLeft" to "20rpx")), "p-3" to padStyleMapOf(utsMapOf("paddingTop" to "30rpx", "paddingRight" to "30rpx", "paddingBottom" to "30rpx", "paddingLeft" to "30rpx")), "p-4" to padStyleMapOf(utsMapOf("paddingTop" to "40rpx", "paddingRight" to "40rpx", "paddingBottom" to "40rpx", "paddingLeft" to "40rpx")));
            }
        val styles1: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("p-5" to padStyleMapOf(utsMapOf("paddingTop" to "50rpx", "paddingRight" to "50rpx", "paddingBottom" to "50rpx", "paddingLeft" to "50rpx")), "pt-0" to padStyleMapOf(utsMapOf("paddingTop" to 0)), "pt" to padStyleMapOf(utsMapOf("paddingTop" to "5rpx")), "pt-1" to padStyleMapOf(utsMapOf("paddingTop" to "10rpx")), "pt-2" to padStyleMapOf(utsMapOf("paddingTop" to "20rpx")), "pt-3" to padStyleMapOf(utsMapOf("paddingTop" to "30rpx")), "pt-4" to padStyleMapOf(utsMapOf("paddingTop" to "40rpx")), "pt-5" to padStyleMapOf(utsMapOf("paddingTop" to "50rpx")), "pb-0" to padStyleMapOf(utsMapOf("paddingBottom" to 0)), "pb-1" to padStyleMapOf(utsMapOf("paddingBottom" to "10rpx")), "pb" to padStyleMapOf(utsMapOf("paddingBottom" to "5rpx")), "pb-2" to padStyleMapOf(utsMapOf("paddingBottom" to "20rpx")), "pb-3" to padStyleMapOf(utsMapOf("paddingBottom" to "30rpx")), "pb-4" to padStyleMapOf(utsMapOf("paddingBottom" to "40rpx")), "pb-5" to padStyleMapOf(utsMapOf("paddingBottom" to "50rpx")), "pl-0" to padStyleMapOf(utsMapOf("paddingLeft" to 0)), "pl" to padStyleMapOf(utsMapOf("paddingLeft" to "5rpx")), "pl-1" to padStyleMapOf(utsMapOf("paddingLeft" to "10rpx")), "pl-2" to padStyleMapOf(utsMapOf("paddingLeft" to "20rpx")), "pl-3" to padStyleMapOf(utsMapOf("paddingLeft" to "30rpx")), "pl-4" to padStyleMapOf(utsMapOf("paddingLeft" to "40rpx")), "pl-5" to padStyleMapOf(utsMapOf("paddingLeft" to "50rpx")), "pr-0" to padStyleMapOf(utsMapOf("paddingRight" to 0)), "pr" to padStyleMapOf(utsMapOf("paddingRight" to "5rpx")), "pr-1" to padStyleMapOf(utsMapOf("paddingRight" to "10rpx")), "pr-2" to padStyleMapOf(utsMapOf("paddingRight" to "20rpx")), "pr-3" to padStyleMapOf(utsMapOf("paddingRight" to "30rpx")), "pr-4" to padStyleMapOf(utsMapOf("paddingRight" to "40rpx")), "pr-5" to padStyleMapOf(utsMapOf("paddingRight" to "50rpx")), "py-0" to padStyleMapOf(utsMapOf("paddingTop" to 0, "paddingBottom" to 0)), "py" to padStyleMapOf(utsMapOf("paddingTop" to "5rpx", "paddingBottom" to "5rpx")), "py-1" to padStyleMapOf(utsMapOf("paddingTop" to "10rpx", "paddingBottom" to "10rpx")), "py-2" to padStyleMapOf(utsMapOf("paddingTop" to "20rpx", "paddingBottom" to "20rpx")), "py-3" to padStyleMapOf(utsMapOf("paddingTop" to "30rpx", "paddingBottom" to "30rpx")), "py-4" to padStyleMapOf(utsMapOf("paddingTop" to "40rpx", "paddingBottom" to "40rpx")), "py-5" to padStyleMapOf(utsMapOf("paddingTop" to "50rpx", "paddingBottom" to "50rpx")), "px-0" to padStyleMapOf(utsMapOf("paddingLeft" to 0, "paddingRight" to 0)), "px-1" to padStyleMapOf(utsMapOf("paddingLeft" to "10rpx", "paddingRight" to "10rpx")), "px" to padStyleMapOf(utsMapOf("paddingLeft" to "5rpx", "paddingRight" to "5rpx")), "px-2" to padStyleMapOf(utsMapOf("paddingLeft" to "20rpx", "paddingRight" to "20rpx")), "px-3" to padStyleMapOf(utsMapOf("paddingLeft" to "30rpx", "paddingRight" to "30rpx")), "px-4" to padStyleMapOf(utsMapOf("paddingLeft" to "40rpx", "paddingRight" to "40rpx")), "px-5" to padStyleMapOf(utsMapOf("paddingLeft" to "50rpx", "paddingRight" to "50rpx")), "font-smaller" to padStyleMapOf(utsMapOf("fontSize" to "15rpx")), "font-small" to padStyleMapOf(utsMapOf("fontSize" to "20rpx")), "font-sm" to padStyleMapOf(utsMapOf("fontSize" to "25rpx")), "font" to padStyleMapOf(utsMapOf("!fontSize" to "30rpx")), "font-md" to padStyleMapOf(utsMapOf("!fontSize" to "35rpx")), "font-lg" to padStyleMapOf(utsMapOf("fontSize" to "40rpx")), "h1" to padStyleMapOf(utsMapOf("fontSize" to "80rpx", "lineHeight" to 1.8)), "h2" to padStyleMapOf(utsMapOf("fontSize" to "60rpx", "lineHeight" to 1.8)), "h3" to padStyleMapOf(utsMapOf("fontSize" to "45rpx", "lineHeight" to 1.8)), "h4" to padStyleMapOf(utsMapOf("fontSize" to "32rpx", "lineHeight" to 1.8)), "h5" to padStyleMapOf(utsMapOf("fontSize" to "30rpx", "lineHeight" to 1.8)), "h6" to padStyleMapOf(utsMapOf("fontSize" to "28rpx", "lineHeight" to 1.8)), "text-left" to padStyleMapOf(utsMapOf("textAlign" to "left")), "text-right" to padStyleMapOf(utsMapOf("textAlign" to "right")), "text-center" to padStyleMapOf(utsMapOf("textAlign" to "center")), "text-ellipsis" to padStyleMapOf(utsMapOf("lines" to 1)), "font-weight-normal" to padStyleMapOf(utsMapOf("fontWeight" to "normal")), "font-weight-bold" to padStyleMapOf(utsMapOf("fontWeight" to "bold")), "font-weight-bolder" to padStyleMapOf(utsMapOf("fontWeight" to "400")), "font-italic" to padStyleMapOf(utsMapOf("fontStyle" to "italic")), "text-white" to padStyleMapOf(utsMapOf("color" to "#ffffff")), "text-primary" to padStyleMapOf(utsMapOf("color" to "#007bff")), "text-hover-primary" to padStyleMapOf(utsMapOf("color" to "#0056b3")), "text-secondary" to padStyleMapOf(utsMapOf("color" to "#6c757d")), "text-hover-secondary" to padStyleMapOf(utsMapOf("color" to "#494f54")), "text-success" to padStyleMapOf(utsMapOf("color" to "#28a745")), "text-hover-success" to padStyleMapOf(utsMapOf("color" to "#19692c")), "text-info" to padStyleMapOf(utsMapOf("color" to "#17a2b8")), "text-hover-info" to padStyleMapOf(utsMapOf("color" to "#0f6674")), "text-warning" to padStyleMapOf(utsMapOf("color" to "#ffc107")), "text-hover-warning" to padStyleMapOf(utsMapOf("color" to "#ba8b00")), "text-danger" to padStyleMapOf(utsMapOf("color" to "#dc3545")), "text-hover-danger" to padStyleMapOf(utsMapOf("color" to "#a71d2a")), "text-light" to padStyleMapOf(utsMapOf("color" to "#f8f9fa")), "text-hover-light" to padStyleMapOf(utsMapOf("color" to "#cbd3da")), "text-dark" to padStyleMapOf(utsMapOf("color" to "#343a40")), "text-hover-dark" to padStyleMapOf(utsMapOf("color" to "#121416")), "text-body" to padStyleMapOf(utsMapOf("color" to "#212529")), "text-muted" to padStyleMapOf(utsMapOf("color" to "#6c757d")), "text-light-muted" to padStyleMapOf(utsMapOf("color" to "#A9A5A0")), "text-light-black" to padStyleMapOf(utsMapOf("color" to "rgba(0,0,0,0.5)")), "text-light-white" to padStyleMapOf(utsMapOf("color" to "rgba(255,255,255,0.5)")), "bg-primary" to padStyleMapOf(utsMapOf("backgroundColor" to "#007bff")), "bg-hover-primary" to padStyleMapOf(utsMapOf("backgroundColor:hover" to "#0062cc")), "bg-secondary" to padStyleMapOf(utsMapOf("backgroundColor" to "#6c757d")), "bg-hover-secondary" to padStyleMapOf(utsMapOf("backgroundColor:hover" to "#545b62")), "bg-success" to padStyleMapOf(utsMapOf("backgroundColor" to "#28a745")), "bg-hover-success" to padStyleMapOf(utsMapOf("backgroundColor" to "#1e7e34")), "bg-info" to padStyleMapOf(utsMapOf("backgroundColor" to "#17a2b8")), "bg-hover-info" to padStyleMapOf(utsMapOf("backgroundColor" to "#117a8b")), "bg-warning" to padStyleMapOf(utsMapOf("backgroundColor" to "#ffc107")), "bg-hover-warning" to padStyleMapOf(utsMapOf("backgroundColor" to "#d39e00")), "bg-danger" to padStyleMapOf(utsMapOf("backgroundColor" to "#dc3545")), "bg-hover-danger" to padStyleMapOf(utsMapOf("backgroundColor" to "#bd2130")), "bg-white" to padStyleMapOf(utsMapOf("backgroundColor" to "#ffffff")), "bg-light" to padStyleMapOf(utsMapOf("backgroundColor" to "#f8f9fa")), "bg-hover-light" to padStyleMapOf(utsMapOf("backgroundColor" to "#dae0e5")));
            }
        val styles2: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("bg-dark" to padStyleMapOf(utsMapOf("backgroundColor" to "#343a40")), "bg-hover-dark" to padStyleMapOf(utsMapOf("backgroundColor" to "#1d2124")), "bg-transparent" to padStyleMapOf(utsMapOf("backgroundColor" to "rgba(0,0,0,0)")), "border" to padStyleMapOf(utsMapOf("borderWidth" to "1rpx", "borderStyle" to "solid", "borderColor" to "#dee2e6")), "border-top" to padStyleMapOf(utsMapOf("borderTopWidth" to "1rpx", "borderTopStyle" to "solid", "borderTopColor" to "#dee2e6")), "border-right" to padStyleMapOf(utsMapOf("borderRightWidth" to "1rpx", "borderRightStyle" to "solid", "borderRightColor" to "#dee2e6")), "border-bottom" to padStyleMapOf(utsMapOf("borderBottomWidth" to "1rpx", "borderBottomStyle" to "solid", "borderBottomColor" to "#dee2e6")), "border-left" to padStyleMapOf(utsMapOf("borderLeftWidth" to "1rpx", "borderLeftStyle" to "solid", "borderLeftColor" to "#dee2e6")), "border-0" to padStyleMapOf(utsMapOf("!borderWidth" to 0)), "border-top-0" to padStyleMapOf(utsMapOf("!borderTopWidth" to 0)), "border-right-0" to padStyleMapOf(utsMapOf("!borderRightWidth" to 0)), "border-bottom-0" to padStyleMapOf(utsMapOf("!borderBottomWidth" to 0)), "border-left-0" to padStyleMapOf(utsMapOf("!borderLeftWidth" to 0)), "border-primary" to padStyleMapOf(utsMapOf("borderColor" to "#007bff")), "border-secondary" to padStyleMapOf(utsMapOf("borderColor" to "#6c757d")), "border-light-secondary" to padStyleMapOf(utsMapOf("borderColor" to "#E9E8E5")), "border-success" to padStyleMapOf(utsMapOf("borderColor" to "#28a745")), "border-info" to padStyleMapOf(utsMapOf("borderColor" to "#17a2b8")), "border-warning" to padStyleMapOf(utsMapOf("borderColor" to "#ffc107")), "border-danger" to padStyleMapOf(utsMapOf("borderColor" to "#dc3545")), "border-light" to padStyleMapOf(utsMapOf("borderColor" to "#f8f9fa")), "border-dark" to padStyleMapOf(utsMapOf("borderColor" to "#343a40")), "border-white" to padStyleMapOf(utsMapOf("borderColor" to "#FFFFFF")), "rounded" to padStyleMapOf(utsMapOf("borderRadius" to "8rpx")), "rounded-lg" to padStyleMapOf(utsMapOf("borderRadius" to "14rpx")), "rounded-top" to padStyleMapOf(utsMapOf("borderTopLeftRadius" to "8rpx", "borderTopRightRadius" to "8rpx")), "rounded-top-lg" to padStyleMapOf(utsMapOf("borderTopLeftRadius" to "14rpx", "borderTopRightRadius" to "14rpx")), "rounded-right" to padStyleMapOf(utsMapOf("borderTopRightRadius" to "8rpx", "borderBottomRightRadius" to "8rpx")), "rounded-bottom" to padStyleMapOf(utsMapOf("borderBottomRightRadius" to "8rpx", "borderBottomLeftRadius" to "8rpx")), "rounded-bottom-lg" to padStyleMapOf(utsMapOf("borderBottomRightRadius" to "14rpx", "borderBottomLeftRadius" to "14rpx")), "rounded-left" to padStyleMapOf(utsMapOf("borderTopLeftRadius" to "8rpx", "borderBottomLeftRadius" to "8rpx")), "rounded-circle" to padStyleMapOf(utsMapOf("borderRadius" to "100rpx")), "rounded-0" to padStyleMapOf(utsMapOf("borderRadius" to 0)), "overflow-hidden" to padStyleMapOf(utsMapOf("overflow" to "hidden")), "position-relative" to padStyleMapOf(utsMapOf("position" to "relative")), "position-absolute" to padStyleMapOf(utsMapOf("position" to "absolute")), "position-fixed" to padStyleMapOf(utsMapOf("position" to "fixed")), "fixed-top" to padStyleMapOf(utsMapOf("position" to "fixed", "top" to 0, "right" to 0, "left" to 0, "zIndex" to 1030)), "fixed-bottom" to padStyleMapOf(utsMapOf("position" to "fixed", "right" to 0, "bottom" to 0, "left" to 0, "zIndex" to 1030)), "top-0" to padStyleMapOf(utsMapOf("top" to 0)), "left-0" to padStyleMapOf(utsMapOf("left" to 0)), "right-0" to padStyleMapOf(utsMapOf("right" to 0)), "bottom-0" to padStyleMapOf(utsMapOf("bottom" to 0)), "mask" to padStyleMapOf(utsMapOf("position" to "fixed", "left" to 0, "right" to 0, "top" to 0, "bottom" to 0, "zIndex" to 100)));
            }
    }
}
val GenAppClass = CreateVueAppComponent(GenApp::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "app", name = "", inheritAttrs = true, inject = Map(), props = Map(), propsNeedCastKeys = utsArrayOf(), emits = Map(), components = Map(), styles = GenApp.styles);
}
, fun(instance): GenApp {
    return GenApp(instance);
}
);
open class UxTheme (
    open var dark: Boolean? = null,
    open var theme: String? = null,
    open var primary: String? = null,
    open var primaryDark: String? = null,
    open var primaryDisabled: String? = null,
    open var primaryLight: String? = null,
    open var warn: String? = null,
    open var warnDark: String? = null,
    open var warnDisabled: String? = null,
    open var warnLight: String? = null,
    open var success: String? = null,
    open var successDark: String? = null,
    open var successDisabled: String? = null,
    open var successLight: String? = null,
    open var error: String? = null,
    open var errorDark: String? = null,
    open var errorDisabled: String? = null,
    open var errorLight: String? = null,
    open var info: String? = null,
    open var infoDark: String? = null,
    open var infoDisabled: String? = null,
    open var infoLight: String? = null,
    open var main: String? = null,
    open var regular: String? = null,
    open var summary: String? = null,
    open var placeholder: String? = null,
    open var title: String? = null,
    open var background: String? = null,
    open var disabled: String? = null,
    open var border: String? = null,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("UxTheme", "uni_modules/ux-frame/libs/core/theme.uts", 5, 13)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return UxThemeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class UxThemeReactiveObject : UxTheme, IUTSReactive<UxTheme> {
    override var __v_raw: UxTheme;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: UxTheme, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(dark = __v_raw.dark, theme = __v_raw.theme, primary = __v_raw.primary, primaryDark = __v_raw.primaryDark, primaryDisabled = __v_raw.primaryDisabled, primaryLight = __v_raw.primaryLight, warn = __v_raw.warn, warnDark = __v_raw.warnDark, warnDisabled = __v_raw.warnDisabled, warnLight = __v_raw.warnLight, success = __v_raw.success, successDark = __v_raw.successDark, successDisabled = __v_raw.successDisabled, successLight = __v_raw.successLight, error = __v_raw.error, errorDark = __v_raw.errorDark, errorDisabled = __v_raw.errorDisabled, errorLight = __v_raw.errorLight, info = __v_raw.info, infoDark = __v_raw.infoDark, infoDisabled = __v_raw.infoDisabled, infoLight = __v_raw.infoLight, main = __v_raw.main, regular = __v_raw.regular, summary = __v_raw.summary, placeholder = __v_raw.placeholder, title = __v_raw.title, background = __v_raw.background, disabled = __v_raw.disabled, border = __v_raw.border) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UxThemeReactiveObject {
        return UxThemeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var dark: Boolean?
        get() {
            return trackReactiveGet(__v_raw, "dark", __v_raw.dark, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("dark")) {
                return;
            }
            val oldValue = __v_raw.dark;
            __v_raw.dark = value;
            triggerReactiveSet(__v_raw, "dark", oldValue, value);
        }
    override var theme: String?
        get() {
            return trackReactiveGet(__v_raw, "theme", __v_raw.theme, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("theme")) {
                return;
            }
            val oldValue = __v_raw.theme;
            __v_raw.theme = value;
            triggerReactiveSet(__v_raw, "theme", oldValue, value);
        }
    override var primary: String?
        get() {
            return trackReactiveGet(__v_raw, "primary", __v_raw.primary, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("primary")) {
                return;
            }
            val oldValue = __v_raw.primary;
            __v_raw.primary = value;
            triggerReactiveSet(__v_raw, "primary", oldValue, value);
        }
    override var primaryDark: String?
        get() {
            return trackReactiveGet(__v_raw, "primaryDark", __v_raw.primaryDark, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("primaryDark")) {
                return;
            }
            val oldValue = __v_raw.primaryDark;
            __v_raw.primaryDark = value;
            triggerReactiveSet(__v_raw, "primaryDark", oldValue, value);
        }
    override var primaryDisabled: String?
        get() {
            return trackReactiveGet(__v_raw, "primaryDisabled", __v_raw.primaryDisabled, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("primaryDisabled")) {
                return;
            }
            val oldValue = __v_raw.primaryDisabled;
            __v_raw.primaryDisabled = value;
            triggerReactiveSet(__v_raw, "primaryDisabled", oldValue, value);
        }
    override var primaryLight: String?
        get() {
            return trackReactiveGet(__v_raw, "primaryLight", __v_raw.primaryLight, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("primaryLight")) {
                return;
            }
            val oldValue = __v_raw.primaryLight;
            __v_raw.primaryLight = value;
            triggerReactiveSet(__v_raw, "primaryLight", oldValue, value);
        }
    override var warn: String?
        get() {
            return trackReactiveGet(__v_raw, "warn", __v_raw.warn, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("warn")) {
                return;
            }
            val oldValue = __v_raw.warn;
            __v_raw.warn = value;
            triggerReactiveSet(__v_raw, "warn", oldValue, value);
        }
    override var warnDark: String?
        get() {
            return trackReactiveGet(__v_raw, "warnDark", __v_raw.warnDark, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("warnDark")) {
                return;
            }
            val oldValue = __v_raw.warnDark;
            __v_raw.warnDark = value;
            triggerReactiveSet(__v_raw, "warnDark", oldValue, value);
        }
    override var warnDisabled: String?
        get() {
            return trackReactiveGet(__v_raw, "warnDisabled", __v_raw.warnDisabled, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("warnDisabled")) {
                return;
            }
            val oldValue = __v_raw.warnDisabled;
            __v_raw.warnDisabled = value;
            triggerReactiveSet(__v_raw, "warnDisabled", oldValue, value);
        }
    override var warnLight: String?
        get() {
            return trackReactiveGet(__v_raw, "warnLight", __v_raw.warnLight, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("warnLight")) {
                return;
            }
            val oldValue = __v_raw.warnLight;
            __v_raw.warnLight = value;
            triggerReactiveSet(__v_raw, "warnLight", oldValue, value);
        }
    override var success: String?
        get() {
            return trackReactiveGet(__v_raw, "success", __v_raw.success, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("success")) {
                return;
            }
            val oldValue = __v_raw.success;
            __v_raw.success = value;
            triggerReactiveSet(__v_raw, "success", oldValue, value);
        }
    override var successDark: String?
        get() {
            return trackReactiveGet(__v_raw, "successDark", __v_raw.successDark, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("successDark")) {
                return;
            }
            val oldValue = __v_raw.successDark;
            __v_raw.successDark = value;
            triggerReactiveSet(__v_raw, "successDark", oldValue, value);
        }
    override var successDisabled: String?
        get() {
            return trackReactiveGet(__v_raw, "successDisabled", __v_raw.successDisabled, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("successDisabled")) {
                return;
            }
            val oldValue = __v_raw.successDisabled;
            __v_raw.successDisabled = value;
            triggerReactiveSet(__v_raw, "successDisabled", oldValue, value);
        }
    override var successLight: String?
        get() {
            return trackReactiveGet(__v_raw, "successLight", __v_raw.successLight, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("successLight")) {
                return;
            }
            val oldValue = __v_raw.successLight;
            __v_raw.successLight = value;
            triggerReactiveSet(__v_raw, "successLight", oldValue, value);
        }
    override var error: String?
        get() {
            return trackReactiveGet(__v_raw, "error", __v_raw.error, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("error")) {
                return;
            }
            val oldValue = __v_raw.error;
            __v_raw.error = value;
            triggerReactiveSet(__v_raw, "error", oldValue, value);
        }
    override var errorDark: String?
        get() {
            return trackReactiveGet(__v_raw, "errorDark", __v_raw.errorDark, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("errorDark")) {
                return;
            }
            val oldValue = __v_raw.errorDark;
            __v_raw.errorDark = value;
            triggerReactiveSet(__v_raw, "errorDark", oldValue, value);
        }
    override var errorDisabled: String?
        get() {
            return trackReactiveGet(__v_raw, "errorDisabled", __v_raw.errorDisabled, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("errorDisabled")) {
                return;
            }
            val oldValue = __v_raw.errorDisabled;
            __v_raw.errorDisabled = value;
            triggerReactiveSet(__v_raw, "errorDisabled", oldValue, value);
        }
    override var errorLight: String?
        get() {
            return trackReactiveGet(__v_raw, "errorLight", __v_raw.errorLight, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("errorLight")) {
                return;
            }
            val oldValue = __v_raw.errorLight;
            __v_raw.errorLight = value;
            triggerReactiveSet(__v_raw, "errorLight", oldValue, value);
        }
    override var info: String?
        get() {
            return trackReactiveGet(__v_raw, "info", __v_raw.info, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("info")) {
                return;
            }
            val oldValue = __v_raw.info;
            __v_raw.info = value;
            triggerReactiveSet(__v_raw, "info", oldValue, value);
        }
    override var infoDark: String?
        get() {
            return trackReactiveGet(__v_raw, "infoDark", __v_raw.infoDark, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("infoDark")) {
                return;
            }
            val oldValue = __v_raw.infoDark;
            __v_raw.infoDark = value;
            triggerReactiveSet(__v_raw, "infoDark", oldValue, value);
        }
    override var infoDisabled: String?
        get() {
            return trackReactiveGet(__v_raw, "infoDisabled", __v_raw.infoDisabled, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("infoDisabled")) {
                return;
            }
            val oldValue = __v_raw.infoDisabled;
            __v_raw.infoDisabled = value;
            triggerReactiveSet(__v_raw, "infoDisabled", oldValue, value);
        }
    override var infoLight: String?
        get() {
            return trackReactiveGet(__v_raw, "infoLight", __v_raw.infoLight, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("infoLight")) {
                return;
            }
            val oldValue = __v_raw.infoLight;
            __v_raw.infoLight = value;
            triggerReactiveSet(__v_raw, "infoLight", oldValue, value);
        }
    override var main: String?
        get() {
            return trackReactiveGet(__v_raw, "main", __v_raw.main, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("main")) {
                return;
            }
            val oldValue = __v_raw.main;
            __v_raw.main = value;
            triggerReactiveSet(__v_raw, "main", oldValue, value);
        }
    override var regular: String?
        get() {
            return trackReactiveGet(__v_raw, "regular", __v_raw.regular, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("regular")) {
                return;
            }
            val oldValue = __v_raw.regular;
            __v_raw.regular = value;
            triggerReactiveSet(__v_raw, "regular", oldValue, value);
        }
    override var summary: String?
        get() {
            return trackReactiveGet(__v_raw, "summary", __v_raw.summary, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("summary")) {
                return;
            }
            val oldValue = __v_raw.summary;
            __v_raw.summary = value;
            triggerReactiveSet(__v_raw, "summary", oldValue, value);
        }
    override var placeholder: String?
        get() {
            return trackReactiveGet(__v_raw, "placeholder", __v_raw.placeholder, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("placeholder")) {
                return;
            }
            val oldValue = __v_raw.placeholder;
            __v_raw.placeholder = value;
            triggerReactiveSet(__v_raw, "placeholder", oldValue, value);
        }
    override var title: String?
        get() {
            return trackReactiveGet(__v_raw, "title", __v_raw.title, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("title")) {
                return;
            }
            val oldValue = __v_raw.title;
            __v_raw.title = value;
            triggerReactiveSet(__v_raw, "title", oldValue, value);
        }
    override var background: String?
        get() {
            return trackReactiveGet(__v_raw, "background", __v_raw.background, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("background")) {
                return;
            }
            val oldValue = __v_raw.background;
            __v_raw.background = value;
            triggerReactiveSet(__v_raw, "background", oldValue, value);
        }
    override var disabled: String?
        get() {
            return trackReactiveGet(__v_raw, "disabled", __v_raw.disabled, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("disabled")) {
                return;
            }
            val oldValue = __v_raw.disabled;
            __v_raw.disabled = value;
            triggerReactiveSet(__v_raw, "disabled", oldValue, value);
        }
    override var border: String?
        get() {
            return trackReactiveGet(__v_raw, "border", __v_raw.border, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("border")) {
                return;
            }
            val oldValue = __v_raw.border;
            __v_raw.border = value;
            triggerReactiveSet(__v_raw, "border", oldValue, value);
        }
}
val defTheme = fun(): UxTheme {
    return UxTheme(dark = false, theme = "", primary = "#3c9cff", primaryDark = "#398ade", primaryDisabled = "#9acafc", primaryLight = "#ecf5ff", success = "#5ac725", successDark = "#53c21d", successDisabled = "#a9e08f", successLight = "#f5fff0", error = "#f56c6c", errorDark = "#e45656", errorDisabled = "#f7b2b2", errorLight = "#fef0f0", warn = "#f9ae3d", warnDark = "#f1a532", warnDisabled = "#f9d39b", warnLight = "#fdf6ec", info = "#909399", infoDark = "#767a82", infoDisabled = "#c4c6c9", infoLight = "#f4f4f5", main = "#303133", regular = "#606266", summary = "#909193", placeholder = "#c0c4cc", title = "#333333", background = "#f3f4f6", disabled = "#c8c9cc", border = "#e7e6e4");
}
;
val SetTheme = fun(theme: UxTheme, reassignedConf: UxTheme?){
    var conf = reassignedConf;
    var def = defTheme();
    conf = conf ?: def;
    theme.dark = conf.dark ?: def.dark;
    theme.theme = conf.theme ?: def.theme;
    theme.primary = conf.primary ?: def.primary;
    theme.primaryDark = conf.primaryDark ?: def.primaryDark;
    theme.primaryDisabled = conf.primaryDisabled ?: def.primaryDisabled;
    theme.primaryLight = conf.primaryLight ?: def.primaryLight;
    theme.warn = conf.warn ?: def.warn;
    theme.warnDark = conf.warnDark ?: def.warnDark;
    theme.warnDisabled = conf.warnDisabled ?: def.warnDisabled;
    theme.warnLight = conf.warnLight ?: def.warnLight;
    theme.success = conf.success ?: def.success;
    theme.successDark = conf.successDark ?: def.successDark;
    theme.successDisabled = conf.successDisabled ?: def.successDisabled;
    theme.successLight = conf.successLight ?: def.successLight;
    theme.error = conf.error ?: def.error;
    theme.errorDark = conf.errorDark ?: def.errorDark;
    theme.errorDisabled = conf.errorDisabled ?: def.errorDisabled;
    theme.errorLight = conf.errorLight ?: def.errorLight;
    theme.info = conf.info ?: def.info;
    theme.infoDark = conf.infoDark ?: def.infoDark;
    theme.infoDisabled = conf.infoDisabled ?: def.infoDisabled;
    theme.infoLight = conf.infoLight ?: def.infoLight;
    theme.main = conf.main ?: def.main;
    theme.regular = conf.regular ?: def.regular;
    theme.summary = conf.summary ?: def.summary;
    theme.placeholder = conf.placeholder ?: def.placeholder;
    theme.title = conf.title ?: def.title;
    theme.background = conf.background ?: def.background;
    theme.disabled = conf.disabled ?: def.disabled;
    theme.border = conf.border ?: def.border;
}
;
var theme = reactive(UxTheme());
open class useColor : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("useColor", "uni_modules/ux-frame/libs/use/useColor.uts", 9, 14);
    }
    private var core: Core;
    constructor(core: Core){
        this.core = core;
    }
    open var hexToRgba = fun(reassignedHex: String?, alpha: Number): String {
        var hex = reassignedHex;
        if (hex == null || hex == "" || hex == "transparent") {
            return "transparent";
        }
        hex = this.parseColor(hex.toLowerCase());
        var f = fun(hex: String, alpha: Number): String {
            var r = parseInt(hex.slice(1, 3), 16);
            var g = parseInt(hex.slice(3, 5), 16);
            var b = parseInt(hex.slice(5, 7), 16);
            return "rgba(" + r + "," + g + "," + b + "," + alpha + ")";
        }
        ;
        if (hex.length == 7 && hex.startsWith("#")) {
            return f(hex, alpha);
        } else if (hex.startsWith("rgb")) {
            hex = this.rgbToHex(hex);
            if (hex == "") {
                return "transparent";
            }
            return f(hex, alpha);
        } else {
            console.error("[ux-frame]\u914D\u7F6E\u9519\u8BEF: " + hex + " \u4EC5\u652F\u6301hex\u548Crgba\u989C\u8272\u503C", " at uni_modules/ux-frame/libs/use/useColor.uts:50");
        }
        return "transparent";
    }
    ;
    open var rgbToHex = fun(rgb: String): String {
        val reg = UTSRegExp("^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})\$", "");
        if (UTSRegExp("^(rgb|RGB)", "").test(rgb)) {
            val aColor = rgb.replace(UTSRegExp("(?:\\(|\\)|rgb|RGB)*", "g"), "").split(",");
            var strHex = "#";
            run {
                var i: Number = 0;
                while(i < aColor.length){
                    var hex = aColor[i];
                    hex = if (hex.length == 1) {
                        "" + 0 + hex;
                    } else {
                        hex;
                    };
                    if (hex == "0") {
                        hex += hex;
                    }
                    strHex += hex;
                    i++;
                }
            }
            if (strHex.length != 7) {
                strHex = rgb;
            }
            return strHex;
        } else if (reg.test(rgb)) {
            val aNum = rgb.replace(UTSRegExp("#", ""), "").split("");
            if (aNum.length == 6) {
                return rgb;
            }
            if (aNum.length == 3) {
                var numHex = "#";
                run {
                    var i: Number = 0;
                    while(i < aNum.length){
                        numHex += (aNum[i] + aNum[i]);
                        i += 1;
                    }
                }
                return numHex;
            }
        }
        return rgb;
    }
    ;
    open var colorToRgba = fun(reassignedColor: String, alpha: Number): String {
        var color = reassignedColor;
        color = this.rgbToHex(color);
        val reg = UTSRegExp("^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})\$", "");
        var sColor = color.toLowerCase();
        if (sColor != "" && reg.test(sColor)) {
            if (sColor.length == 4) {
                var sColorNew = "#";
                run {
                    var i: Number = 1;
                    while(i < 4){
                        sColorNew += sColor.slice(i, i + 1).concat(sColor.slice(i, i + 1));
                        i += 1;
                    }
                }
                sColor = sColorNew;
            }
            val sColorChange = utsArrayOf<Number>();
            run {
                var i: Number = 1;
                while(i < 7){
                    sColorChange.push(parseInt("0x" + sColor.slice(i, i + 2)));
                    i += 2;
                }
            }
            return "rgba(" + sColorChange.join(",") + "," + alpha + ")";
        }
        return sColor;
    }
    ;
    open var colorGradient = fun(startColor: String, endColor: String, step: Number): UTSArray<String> {
        var _hexToRgb = fun(reassignedSColor: String): UTSArray<Number> {
            var sColor = reassignedSColor;
            val reg = UTSRegExp("^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})\$", "");
            sColor = sColor.toLowerCase();
            if (sColor != "" && reg.test(sColor)) {
                if (sColor.length == 4) {
                    var sColorNew = "#";
                    run {
                        var i: Number = 1;
                        while(i < 4){
                            sColorNew += sColor.slice(i, i + 1).concat(sColor.slice(i, i + 1));
                            i += 1;
                        }
                    }
                    sColor = sColorNew;
                }
                val sColorChange = utsArrayOf<Number>();
                run {
                    var i: Number = 1;
                    while(i < 7){
                        sColorChange.push(parseInt("0x" + sColor.slice(i, i + 2)));
                        i += 2;
                    }
                }
                return sColorChange;
            }
            if (UTSRegExp("^(rgb|RGB)", "").test(sColor)) {
                val arr = sColor.replace(UTSRegExp("(?:\\(|\\)|rgb|RGB)*", "g"), "").split(",");
                return arr.map(fun(kVal: String): Number {
                    return parseInt(kVal);
                }
                );
            }
            return utsArrayOf();
        }
        ;
        val startRGB = _hexToRgb(startColor);
        val endRGB = _hexToRgb(endColor);
        if (startRGB.length == 0 || endRGB.length == 0) {
            return utsArrayOf(
                startColor,
                endColor
            );
        }
        val startR = startRGB[0];
        val startG = startRGB[1];
        val startB = startRGB[2];
        val endR = endRGB[0];
        val endG = endRGB[1];
        val endB = endRGB[2];
        val sR = (endR - startR) / step;
        val sG = (endG - startG) / step;
        val sB = (endB - startB) / step;
        val colorArr = utsArrayOf<String>();
        run {
            var i: Number = 0;
            while(i < step){
                var hex = this.rgbToHex("rgb(" + Math.round((sR * i + startR)) + "," + Math.round((sG * i + startG)) + "," + Math.round((sB * i + startB)) + ")");
                if (i == 0) {
                    hex = this.rgbToHex(startColor);
                }
                if (i == step - 1) {
                    hex = this.rgbToHex(endColor);
                }
                colorArr.push(hex);
                i++;
            }
        }
        return colorArr;
    }
    ;
    open fun parseColor(color: String): String {
        val colors = object : UTSJSONObject() {
            var white = "#ffffff"
            var black = "#000000"
            var red = "#ff0000"
            var green = "#00ff00"
            var blue = "#0000ff"
            var yellow = "#ffff00"
            var cyan = "#00ffff"
            var magenta = "#ff00ff"
            var lime = "#00ff00"
            var maroon = "#800000"
            var olive = "#808000"
            var silver = "#c0c0c0"
            var teal = "#008080"
        };
        if (colors[color] == null) {
            return color;
        } else {
            return colors[color] as String;
        }
    }
}
open class useDate {
    open var core: Core;
    constructor(core: Core){
        this.core = core;
    }
    open var toDate = fun(reassignedDate: String): Date {
        var date = reassignedDate;
        date = date.split("周")[0].trim().split("星期")[0].trim().split("礼拜")[0].trim();
        if (date == "") {
            return Date();
        }
        if (date.length >= 10 && UTSRegExp("^\\d+\$", "").test(date)) {
            var timestamp = parseInt(date);
            if (UTSRegExp("^\\d{10}\$", "").test(timestamp.toString())) {
                return Date(timestamp * 1000);
            } else {
                return Date(timestamp);
            }
        } else {
            if (!date.includes("T")) {
                date = date.replace(UTSRegExp("\\/", "g"), "-").replace(UTSRegExp("年", "g"), "-").replace(UTSRegExp("月", "g"), "-").replace(UTSRegExp("日", "g"), "").replace(UTSRegExp("时", "g"), ":").replace(UTSRegExp("分", "g"), ":").replace(UTSRegExp("秒", "g"), "").replace(UTSRegExp("^-+|-+\$", "g"), "").trim();
                if (date.length == 4) {
                    date += "-01-01 00:00:00";
                } else if (date.length == 7) {
                    date += "-01 00:00:00";
                } else if (date.length == 8) {
                    date = "" + this.today() + " " + date;
                } else if (date.length == 10) {
                    date += " 00:00:00";
                } else if (date.length == 13) {
                    date += ":00:00";
                } else if (date.length == 16) {
                    date += ":00";
                }
                var d = date.split(UTSRegExp("[^0-9]", ""));
                try {
                    return Date(parseInt(d[0]), parseInt(d[1]) - 1, parseInt(d[2]), parseInt(d[3]), parseInt(d[4]), parseInt(d[5]));
                } catch (e: Throwable) {
                    console.error("[ux-date]\u89E3\u6790\u5931\u8D25\uFF1A" + date, " at uni_modules/ux-frame/libs/use/useDate.uts:71");
                    return Date();
                }
            } else {
                return Date(date);
            }
        }
    }
    ;
    open var now = fun(): String {
        var date = Date();
        var year = date.getFullYear();
        var month = ("" + (date.getMonth() + 1)).padStart(2, "0");
        var day = ("" + date.getDate()).padStart(2, "0");
        var hour = ("" + date.getHours()).padStart(2, "0");
        var minute = ("" + date.getMinutes()).padStart(2, "0");
        var second = ("" + date.getSeconds()).padStart(2, "0");
        return "" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }
    ;
    open var timestamp = fun(): Number {
        return Date().getTime();
    }
    ;
    open var nowYear = fun(): Number {
        return Date().getFullYear();
    }
    ;
    open var lastYear = fun(): Number {
        return this.nowYear() - 1;
    }
    ;
    open var nextYear = fun(): Number {
        return this.nowYear() + 1;
    }
    ;
    open var years = fun(min: Number, max: Number): UTSArray<UTSJSONObject> {
        var years: UTSArray<UTSJSONObject> = utsArrayOf();
        var d31: UTSArray<UTSJSONObject> = utsArrayOf();
        run {
            var i: Number = 1;
            while(i <= 31){
                d31.push(object : UTSJSONObject() {
                    var value = i
                    var label = i.toString().padStart(2, "0")
                });
                i++;
            }
        }
        var d30: UTSArray<UTSJSONObject> = utsArrayOf();
        run {
            var i: Number = 1;
            while(i <= 30){
                d30.push(object : UTSJSONObject() {
                    var value = i
                    var label = i.toString().padStart(2, "0")
                });
                i++;
            }
        }
        var d28: UTSArray<UTSJSONObject> = utsArrayOf();
        run {
            var i: Number = 1;
            while(i <= 28){
                d28.push(object : UTSJSONObject() {
                    var value = i
                    var label = i.toString().padStart(2, "0")
                });
                i++;
            }
        }
        var d29: UTSArray<UTSJSONObject> = utsArrayOf();
        run {
            var i: Number = 1;
            while(i <= 29){
                d29.push(object : UTSJSONObject() {
                    var value = i
                    var label = i.toString().padStart(2, "0")
                });
                i++;
            }
        }
        var months: UTSArray<UTSJSONObject> = utsArrayOf();
        run {
            var i: Number = 1;
            while(i <= 12){
                var children: UTSArray<UTSJSONObject>;
                if (i == 4 || i == 6 || i == 9 || i == 11) {
                    children = d30;
                } else if (i == 2) {
                    children = d28;
                } else {
                    children = d31;
                }
                months.push(object : UTSJSONObject() {
                    var value = i
                    var label = i.toString().padStart(2, "0")
                    var children = children
                });
                i++;
            }
        }
        var start = Date().getFullYear() + max;
        var end = start - max - min;
        run {
            var i = start;
            var length = end;
            while(i >= length){
                var children = months.slice();
                if (i % 4 == 0) {
                    children[1]["children"] = d29;
                }
                years.push(object : UTSJSONObject() {
                    var value = i
                    var label = i
                    var children = children
                });
                i--;
            }
        }
        return years;
    }
    ;
    open fun month(n: Number = 0): String {
        var date = Date();
        date.setMonth(date.getMonth() + n);
        var year = date.getFullYear();
        var month = ("" + (date.getMonth() + 1)).padStart(2, "0");
        return "" + year + "-" + month;
    }
    open var lastMonth = fun(): String {
        return this.month(-1);
    }
    ;
    open var nextMonth = fun(): String {
        return this.month(1);
    }
    ;
    open var monthDays = fun(year: Number, month: Number): Number {
        var days: Number = 31;
        if (year % 4 == 0 && month == 2) {
            days = 29;
        } else {
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                days = 30;
            } else if (month == 2) {
                days = 28;
            }
        }
        return days;
    }
    ;
    open fun monthDates(date: String): UTSArray<String> {
        val d = this.toDate(date);
        var year = d.getFullYear();
        var month = d.getMonth() + 1;
        val days = Date(year, month, 0).getDate();
        val result: UTSArray<String> = utsArrayOf();
        run {
            var day: Number = 1;
            while(day <= days){
                result.push("" + year + "-" + month.toString().padStart(2, "0") + "-" + day.toString().padStart(2, "0"));
                day++;
            }
        }
        return result;
    }
    open fun months(smonth: String, emonth: String): UTSArray<String> {
        val fDate = this.toDate(smonth);
        val eDate = this.toDate(emonth);
        fDate.setDate(fDate.getDate() - fDate.getDay());
        eDate.setDate(eDate.getDate() - eDate.getDay() + 6);
        val result: UTSArray<String> = utsArrayOf();
        run {
            var d = fDate;
            while(d.getTime() <= eDate.getTime()){
                var month = d.toISOString().slice(0, 7);
                if (result.indexOf(month) == -1) {
                    result.push(month);
                }
                d.setDate(d.getDate() + 1);
            }
        }
        return result;
    }
    open var week = fun(): String {
        return this.weekName(Date().getDay(), "周");
    }
    ;
    open var weekName = fun(day: Number, format: String): String {
        if (format.indexOf("星期") != -1) {
            return utsArrayOf(
                "星期天",
                "星期一",
                "星期二",
                "星期三",
                "星期四",
                "星期五",
                "星期六"
            )[day];
        } else if (format.indexOf("礼拜") != -1) {
            return utsArrayOf(
                "礼拜天",
                "礼拜一",
                "礼拜二",
                "礼拜三",
                "礼拜四",
                "礼拜五",
                "礼拜六"
            )[day];
        } else {
            return utsArrayOf(
                "周日",
                "周一",
                "周二",
                "周三",
                "周四",
                "周五",
                "周六"
            )[day];
        }
    }
    ;
    open var weeks = fun(): UTSArray<String> {
        val fDate = Date();
        val eDate = Date();
        fDate.setDate(fDate.getDate() - fDate.getDay() + 1);
        eDate.setDate(eDate.getDate() - eDate.getDay() + 7);
        val result: UTSArray<String> = utsArrayOf();
        run {
            var d = fDate;
            while(d.getTime() <= eDate.getTime()){
                result.push(d.toISOString().slice(0, 10));
                d.setDate(d.getDate() + 1);
            }
        }
        return result;
    }
    ;
    open fun today(n: Number = 0): String {
        var date = Date();
        date.setDate(date.getDate() + n);
        var year = date.getFullYear();
        var month = ("" + (date.getMonth() + 1)).padStart(2, "0");
        var day = ("" + date.getDate()).padStart(2, "0");
        return "" + year + "-" + month + "-" + day;
    }
    open var yesterday = fun(): String {
        return this.today(-1);
    }
    ;
    open var tomorrow = fun(): String {
        return this.today(1);
    }
    ;
    open fun dates(sdate: String, edate: String): UTSArray<String> {
        val startDate = this.toDate(sdate);
        val endDate = this.toDate(edate);
        val result: UTSArray<String> = utsArrayOf();
        while(startDate.getTime() <= endDate.getTime()){
            result.push(startDate.toISOString().slice(0, 10));
            startDate.setDate(startDate.getDate() + 1);
        }
        return result;
    }
    open var time = fun(): String {
        var date = Date();
        var hour = ("" + date.getHours()).padStart(2, "0");
        var minute = ("" + date.getMinutes()).padStart(2, "0");
        var second = ("" + date.getSeconds()).padStart(2, "0");
        return "" + hour + ":" + minute + ":" + second;
    }
    ;
    open fun compare(sdate: String, edate: String, equal: Boolean = false): Boolean {
        val startDate = this.toDate(sdate).getTime();
        val endDate = this.toDate(edate).getTime();
        if (equal) {
            return startDate <= endDate;
        } else {
            return startDate < endDate;
        }
    }
    open var toTimestamp = fun(date: String): Number {
        return this.toDate(date).getTime();
    }
    ;
    open var toMillisecond = fun(time: String): Number {
        val _time_split_map = time.split(":").map(fun(e: String): Number {
            return parseInt(e);
        }
        );
        val hours = _time_split_map[0];
        val minutes = _time_split_map[1];
        val seconds = _time_split_map[2];
        return (hours * 60 * 60 * 1000) + (minutes * 60 * 1000) + (seconds * 1000);
    }
    ;
    open fun fmtDate(reassignedDate: String, reassignedFormat: String): String {
        var format = reassignedFormat;
        var date = reassignedDate;
        if (format == "") {
            format = "yyyy-MM-dd";
        }
        date = date.split("周")[0].trim().split("星期")[0].trim().split("礼拜")[0].trim();
        var d = this.toDate(date);
        var timeSource = Map<String, String>();
        timeSource.set("y", d.getFullYear().toString());
        timeSource.set("M", (d.getMonth() + 1).toString().padStart(2, "0"));
        timeSource.set("d", d.getDate().toString().padStart(2, "0"));
        timeSource.set("H", d.getHours().toString().padStart(2, "0"));
        timeSource.set("m", d.getMinutes().toString().padStart(2, "0"));
        timeSource.set("s", d.getSeconds().toString().padStart(2, "0"));
        var result = format.split("周")[0].trim().split("星期")[0].trim().split("礼拜")[0].trim();
        timeSource.forEach(fun(v: String, key: String){
            val rets = UTSRegExp("" + key + "+").exec(result) ?: utsArrayOf<UTSRegExp>();
            if (rets.length > 0) {
                result = result.replace(rets[0].toString(), v);
            }
        }
        );
        var fmtWeek = format.indexOf("周") != -1 || format.indexOf("星期") != -1 || format.indexOf("礼拜") != -1;
        if (fmtWeek) {
            result += " " + this.weekName(this.toDate(result).getDay(), format);
        }
        return result;
    }
}
open class useFmt {
    open var core: Core;
    constructor(core: Core){
        this.core = core;
    }
    open fun encryptText(text: String): String {
        if (text.length == 2) {
            return text[0] + "*";
        } else if (text.length > 2) {
            return text[0] + "*".repeat(text.length - 2) + text[text.length - 1];
        } else {
            return text;
        }
    }
    open fun upperMoney(money: String): String {
        if (money == "") {
            return "";
        }
        var cnNums = utsArrayOf(
            "零",
            "壹",
            "贰",
            "叁",
            "肆",
            "伍",
            "陆",
            "柒",
            "捌",
            "玖"
        );
        var cnIntRadice = utsArrayOf(
            "",
            "拾",
            "佰",
            "仟"
        );
        var cnIntUnits = utsArrayOf(
            "",
            "万",
            "亿",
            "兆"
        );
        var cnDecUnits = utsArrayOf(
            "角",
            "分",
            "毫",
            "厘"
        );
        var cnInteger = "";
        var cnIntLast = "元";
        var maxNum: Number = 999999999999999.9999;
        var integerNum: String;
        var decimalNum: String;
        var chineseStr = "";
        var parts: UTSArray<String>;
        var _money = parseFloat(money.toString());
        if (_money >= maxNum) {
            return "";
        }
        if (_money == 0) {
            return "" + cnNums[0] + cnIntLast + cnInteger;
        }
        if (money.indexOf(".") == -1) {
            integerNum = money;
            decimalNum = "";
        } else {
            parts = money.split(".");
            integerNum = parts[0];
            decimalNum = parts[1].substring(0, 4);
        }
        if (parseInt(integerNum, 10) > 0) {
            var zeroCount: Number = 0;
            var len = integerNum.length;
            run {
                var i: Number = 0;
                while(i < len){
                    var n = integerNum.substring(i, i + 1);
                    var p = len - i - 1;
                    var q = p / 4;
                    var m = p % 4;
                    if (n == "0") {
                        zeroCount++;
                    } else {
                        if (zeroCount > 0) {
                            chineseStr += cnNums[0];
                        }
                        zeroCount = 0;
                        chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
                    }
                    if (m == 0 && zeroCount < 4) {
                        chineseStr += cnIntUnits[q];
                    }
                    i++;
                }
            }
            chineseStr += cnIntLast;
        }
        if (decimalNum != "") {
            var decLen = decimalNum.length;
            run {
                var i: Number = 0;
                while(i < decLen){
                    var n = decimalNum.substring(i, i + 1);
                    if (n != "0") {
                        var index = parseInt(n);
                        chineseStr += (cnNums[index] + cnDecUnits[i]);
                    }
                    i++;
                }
            }
        }
        if (chineseStr == "") {
            chineseStr += cnNums[0] + cnIntLast + cnInteger;
        } else if (decimalNum == "") {
            chineseStr += cnInteger;
        }
        return chineseStr;
    }
    open fun fmtMoney(money: String, wfz: Boolean): String {
        try {
            var fIndex = if (wfz) {
                4;
            } else {
                3;
            }
            ;
            if (fIndex == 3) {
                var str = money.toString();
                if (str.indexOf(".") == -1) {
                    return str.replace(UTSRegExp("(\\d)(?=(?:\\d{3})+\$)", "g"), "\$1,");
                } else {
                    var strs = str.split(".");
                    var kVal = strs[0].replace(UTSRegExp("(\\d)(?=(?:\\d{3})+\$)", "g"), "\$1,");
                    run {
                        var i: Number = 1;
                        while(i < strs.length){
                            kVal += "." + strs[i];
                            i++;
                        }
                    }
                    return kVal;
                }
            } else {
                var str = money.toString();
                if (str.indexOf(".") == -1) {
                    return str.replace(UTSRegExp("(\\d)(?=(?:\\d{4})+\$)", "g"), "\$1,");
                } else {
                    var strs = str.split(".");
                    var kVal = strs[0].replace(UTSRegExp("(\\d)(?=(?:\\d{4})+\$)", "g"), "\$1,");
                    run {
                        var i: Number = 1;
                        while(i < strs.length){
                            kVal += "." + strs[i];
                            i++;
                        }
                    }
                    return kVal;
                }
            }
        }
         catch (e: Throwable) {
            console.log(e, " at uni_modules/ux-frame/libs/use/useFmt.uts:180");
        }
        return money.toString();
    }
}
open class useObj {
    open var core: Core;
    constructor(core: Core){
        this.core = core;
    }
    open fun removeNulls(obj: UTSJSONObject?): UTSJSONObject {
        if (obj == null) {
            return UTSJSONObject();
        }
        var data: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("data", "uni_modules/ux-frame/libs/use/useObj.uts", 49, 13)) {
        };
        for(k in obj){
            if (obj.get(k) != null) {
                var _obj = obj.get(k)!!;
                if (_obj is UTSJSONObject) {
                    data.set(k, this.handleJson(_obj));
                } else if (_obj is UTSArray<*>) {
                    data.set(k, this.handleArray(_obj as UTSArray<Any>));
                } else {
                    data.set(k, _obj);
                }
            }
        }
        return data;
    }
    private fun handleJson(obj: UTSJSONObject): UTSJSONObject {
        var data: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("data", "uni_modules/ux-frame/libs/use/useObj.uts", 69, 13)) {
        };
        for(k in obj){
            if (obj.get(k) != null) {
                var _obj = obj.get(k)!!;
                if (_obj is UTSJSONObject) {
                    data.set(k, this.handleJson(_obj));
                } else if (_obj is UTSArray<*>) {
                    data.set(k, this.handleArray(_obj as UTSArray<Any>));
                } else {
                    data.set(k, _obj);
                }
            }
        }
        return data;
    }
    private fun handleArray(objs: UTSArray<Any>): UTSArray<Any> {
        var datas = UTSArray<Any>();
        run {
            var i: Number = 0;
            while(i < objs.length){
                var obj = objs[i];
                if (obj is UTSJSONObject) {
                    datas.push(this.handleJson(obj));
                } else if (obj is UTSArray<*>) {
                    datas.push(this.handleArray(obj as UTSArray<Any>));
                } else {
                    datas.push(obj);
                }
                i++;
            }
        }
        return datas;
    }
}
open class useRandom {
    open var core: Core;
    constructor(core: Core){
        this.core = core;
    }
    open var uuid = fun(): String {
        val chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");
        val uuid: UTSArray<String> = utsArrayOf();
        run {
            var i: Number = 0;
            while(i < 36){
                var r = 0 or Math.random() * 16;
                var c = chars[if ((i == 19)) {
                    (r and 0x3) or 0x8;
                } else {
                    r;
                }
                ];
                uuid.push(c);
                i++;
            }
        }
        uuid[8] = "-";
        uuid[13] = "-";
        uuid[18] = "-";
        uuid[23] = "-";
        uuid[14] = "4";
        return "" + uuid.join("");
    }
    ;
    open var range = fun(min: Number, max: Number, value: Number): Number {
        return Math.max(min, Math.min(max, value));
    }
    ;
    open var random = fun(min: Number, max: Number): Number {
        if (min >= 0 && max > 0 && max >= min) {
            val gab = max - min + 1;
            return Math.floor(Math.random() * gab + min);
        }
        return 0;
    }
    ;
    open fun generateRandom(n: Number): Number {
        if (n == 0) {
            return 0;
        }
        val min = Math.pow(10, n - 1);
        val max = Math.pow(10, n);
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
    open var randomArray = fun(array: UTSArray<Any>): UTSArray<Any> {
        return array.sort(fun(_: Any, _: Any): Number {
            return Math.random() - 0.5;
        }
        );
    }
    ;
    open var trim = fun(str: String, pos: String): String {
        when (pos) {
            "both" -> 
                return str.replace(UTSRegExp("^\\s+|\\s+\$", "g"), "");
            "left" -> 
                return str.replace(UTSRegExp("^\\s*", ""), "");
            "right" -> 
                return str.replace(UTSRegExp("(\\s*\$)", "g"), "");
            "all" -> 
                return str.replace(UTSRegExp("\\s+", "g"), "");
        }
        return str.replace(UTSRegExp("\\s+", "g"), "");
    }
    ;
}
open class useVerify {
    open var core: Core;
    constructor(core: Core){
        this.core = core;
    }
    open fun isDate(date: Any): Boolean {
        if (date == "" || date == 0) {
            return false;
        }
        return !UTSRegExp("Invalid|NaN", "").test(Date(date.toString()).toString());
    }
    open fun isURL(url: String): Boolean {
        val reg = UTSRegExp("^((https|http|ftp|rtsp|mms):\\/\\/)(([0-9a-zA-Z_!~*'().&=+\$%-]+: )?[0-9a-zA-Z_!~*'().&=+\$%-]+@)?(([0-9]{1,3}.){3}[0-9]{1,3}|([0-9a-zA-Z_!~*'()-]+.)*([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z].[a-zA-Z]{2,6})(:[0-9]{1,4})?((\\/?)|(\\/[0-9a-zA-Z_!~*'().;?:@&=+\$,%#-]+)+\\/?)\$", "");
        return reg.test(url);
    }
    open fun isNumber(num: String): Boolean {
        val reg = UTSRegExp("^-?[1-9][0-9]?.?[0-9]*\$", "");
        return reg.test(num);
    }
    open fun isAbc(str: String): Boolean {
        val reg = UTSRegExp("^[A-Za-z]+\$", "");
        return reg.test(str);
    }
    open fun isAbcNum(str: String): Boolean {
        val reg = UTSRegExp("^[0-9a-zA-Z]*\$", "g");
        return reg.test(str);
    }
    open fun isChinese(str: String): Boolean {
        val reg = UTSRegExp("[\\u4E00-\\u9FA5]", "g");
        return reg.test(str);
    }
    open fun isEmail(email: String): Boolean {
        val reg = UTSRegExp("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+\$", "");
        return reg.test(email);
    }
    open fun isPhone(phone: String): Boolean {
        val reg = UTSRegExp("^[1][3,4,5,6,7,8,9][0-9]{9}\$", "");
        return reg.test(phone);
    }
    open fun isLandline(phone: String): Boolean {
        val reg = UTSRegExp("^\\d{3,4}-\\d{7,8}(-\\d{3,4})?\$", "");
        return reg.test(phone);
    }
    open fun isIdcard(idcard: String): Boolean {
        return false;
    }
    open fun isImage(url: String): Boolean {
        val reg = UTSRegExp("\\.(jpeg|jpg|gif|png|svg|webp|jfif|bmp|dpg)", "i");
        return reg.test(url.split("?")[0]);
    }
    open fun isVideo(url: String): Boolean {
        val reg = UTSRegExp("\\.(mp4|mpg|mpeg|dat|asf|avi|rm|rmvb|mov|wmv|flv|mkv|m3u8)", "i");
        return reg.test(url.split("?")[0]);
    }
    open fun isCarNo(no: String): Boolean {
        val xreg = UTSRegExp("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF]\$)|([DF][A-HJ-NP-Z0-9][0-9]{4}\$))", "");
        val creg = UTSRegExp("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1}\$", "");
        if (no.length == 7) {
            return creg.test(no);
        }
        if (no.length == 8) {
            return xreg.test(no);
        }
        return false;
    }
}
open class useUtil {
    open var core: Core;
    constructor(core: Core){
        this.core = core;
    }
    open fun rpx2px(value: Number): Number {
        return UTSAndroid.rpx2px(value);
    }
    open fun getPx(value: Any): Number {
        var _value = value.toString().toLowerCase();
        if (_value.indexOf("%") != -1) {
            try {
                return uni_getSystemInfoSync().windowWidth * (parseFloat(_value.replace("%", "")) / 100);
            }
             catch (e: Throwable) {}
        }
        if (this.core.Verify.isNumber(_value)) {
            return parseFloat(_value);
        }
        if (UTSRegExp("(rpx|upx)\$", "").test(_value)) {
            try {
                var n = parseInt(_value.replace(UTSRegExp("rpx", "g"), "").replace(UTSRegExp("upx", "g"), ""));
                return this.rpx2px(n);
            }
             catch (e: Throwable) {}
        }
        return parseFloat(_value.replace("px", ""));
    }
    open fun addUnit(value: Any, unit: String = this.core.unit): String {
        var _value = value.toString().toLowerCase();
        if (_value.indexOf("%") != -1) {
            return _value;
        }
        if (this.core.Verify.isNumber(_value)) {
            return "" + _value + unit;
        }
        if (UTSRegExp("(rpx|upx)\$", "").test(_value)) {
            try {
                var n = parseInt(_value.replace(UTSRegExp("rpx", "g"), "").replace(UTSRegExp("upx", "g"), ""));
                return "" + this.rpx2px(n) + unit;
            }
             catch (e: Throwable) {}
        }
        return _value;
    }
    open fun `$findEl`(context: ComponentPublicInstance, refs: UTSArray<String>): Element? {
        if (context.`$parent` != null) {
            run {
                var index: Number = 0;
                while(index < refs.length){
                    var name = refs[index];
                    if (context.`$parent`!!.`$refs`[name] != null) {
                        return context.`$parent`!!.`$refs`[name] as Element;
                    } else {
                        run {
                            var i: Number = 0;
                            while(i < context.`$parent`!!.`$children`.length){
                                var child = context.`$parent`!!.`$children`[i];
                                if (child.`$refs`[name] != null) {
                                    return child.`$refs`[name] as Element;
                                }
                                i++;
                            }
                        }
                    }
                    index++;
                }
            }
        }
        return null;
    }
    open fun `$dispatch`(context: ComponentPublicInstance, componentName: String, eventName: String, vararg spreadParams: Any) {
        var params = UTSArray(*spreadParams);
        var parent = context.`$parent`;
        var name = parent?.`$options`?.name;
        while(parent != null && (name == null || componentName != name)){
            parent = parent.`$parent`;
            if (parent != null) {
                name = parent.`$options`.name;
            }
        }
        if (parent != null) {
            parent.`$callMethod`(eventName, *params.toTypedArray());
        }
    }
    open var timer: Number = 0;
    open var flag = false;
    open var throttle = fun(func: () -> Unit, wait: Number): Unit {
        if (wait <= 0) {
            if (!this.flag) {
                this.flag = true;
                func();
                this.timer = setTimeout(fun(){
                    this.flag = false;
                }
                , wait);
            }
        } else if (!this.flag) {
            this.flag = true;
            this.timer = setTimeout(fun(){
                this.flag = false;
                func();
            }
            , wait);
        }
    }
    ;
    open var debounce = fun(func: () -> Unit, wait: Number): Unit {
        clearTimeout(this.timer);
        if (wait <= 0) {
            func();
        } else {
            this.timer = setTimeout(fun(){
                func();
            }
            , wait);
        }
    }
    ;
    open fun xStyle(css: Map<String, Any?>, margin: UTSArray<Any>, mt: Number, mr: Number, mb: Number, ml: Number, padding: UTSArray<Any>, pt: Number, pr: Number, pb: Number, pl: Number): Map<String, Any?> {
        if (margin.length > 0) {
            css.set("margin", margin.map(fun(n: Any): String {
                return this.addUnit(n);
            }).join(" "));
        } else {
            if (mt != 0) {
                css.set("margin-top", this.addUnit(mt));
            }
            if (mr != 0) {
                css.set("margin-right", this.addUnit(mr));
            }
            if (mb != 0) {
                css.set("margin-bottom", this.addUnit(mb));
            }
            if (ml != 0) {
                css.set("margin-left", this.addUnit(ml));
            }
        }
        if (padding.length > 0) {
            css.set("padding", padding.map(fun(n: Any): String {
                return this.addUnit(n);
            }).join(" "));
        } else {
            if (pt != 0) {
                css.set("padding-top", this.addUnit(pt));
            }
            if (pr != 0) {
                css.set("padding-right", this.addUnit(pr));
            }
            if (pb != 0) {
                css.set("padding-bottom", this.addUnit(pb));
            }
            if (pl != 0) {
                css.set("padding-left", this.addUnit(pl));
            }
        }
        return css;
    }
}
open class useCanvas {
    open var core: Core;
    constructor(core: Core){
        this.core = core;
    }
}
open class Socket {
    private var core: Core;
    private var socket: SocketTask? = null;
    private var url: String = "";
    private var isConnected: Boolean = false;
    private var isClosed: Boolean = false;
    private var timer: Number? = null;
    private var reconnectTimer: Number? = null;
    private var msgQueue: UTSArray<String> = utsArrayOf();
    open var onMsg: ((msg: Any) -> Unit)? = null;
    constructor(core: Core){
        this.core = core;
    }
    open fun connect(url: String) {
        if (this.isConnected) {
            return;
        }
        try {
            this.url = url;
            this.openSocket();
            this.clearTimer();
            this.reconnectTimer = setInterval(fun(){
                if (!this.isConnected && !this.isClosed) {
                    this.closeSocket();
                    setTimeout(fun(){
                        this.openSocket();
                    }
                    , 200);
                }
            }
            , 20 * 1000);
        }
         catch (e: Throwable) {
            console.log(e, " at uni_modules/ux-frame/libs/use/useSocket.uts:81");
        }
    }
    open fun close() {
        try {
            this.closeSocket();
            this.clearTimer();
            this.msgQueue = utsArrayOf();
            this.isClosed = true;
            this.socket = null;
        }
         catch (e: Throwable) {
            console.log(e, " at uni_modules/ux-frame/libs/use/useSocket.uts:97");
        }
    }
    open fun send(data: Any) {
        var msg: String;
        if (UTSAndroid.`typeof`( data) == "object") {
            msg = JSON.stringify(data);
        } else {
            msg = "" + data;
        }
        if (!this.isConnected || this.socket == null) {
            this.msgQueue.push(msg);
        } else {
            this.socket!!.send(SendSocketMessageOptions(data = msg));
        }
    }
    private fun openSocket() {
        this.socket = uni_connectSocket(ConnectSocketOptions(url = this.url, success = fun(_){
            setTimeout(fun(){
                this.onOpen();
            }
            , 20);
        }
        , fail = fun(err){
            console.log("[ux-socket] 连接Socket失败", err, " at uni_modules/ux-frame/libs/use/useSocket.uts:131");
        }
        ));
    }
    private fun onOpen() {
        if (this.socket == null) {
            return;
        }
        this.socket!!.onOpen(fun(_){
            console.log("[ux-socket] 已连接", " at uni_modules/ux-frame/libs/use/useSocket.uts:142");
            this.isConnected = true;
            this.isClosed = false;
            this.heartBeat();
            run {
                var i: Number = 0;
                while(i < this.msgQueue.length){
                    this.send(this.msgQueue[i]);
                    i++;
                }
            }
            this.msgQueue = utsArrayOf();
        }
        );
        this.socket!!.onError(fun(_){
            console.log("[ux-socket] 连接失败", " at uni_modules/ux-frame/libs/use/useSocket.uts:158");
            this.isConnected = false;
            this.clearTimer();
        }
        );
        this.socket!!.onClose(fun(_){
            console.log("[ux-socket] 已关闭", " at uni_modules/ux-frame/libs/use/useSocket.uts:164");
            this.isConnected = false;
            this.clearTimer();
        }
        );
        this.socket!!.onMessage(fun(res){
            if (this.isJson(res.data)) {
                var data = UTSAndroid.consoleDebugError(JSON.parse(res.data as String), " at uni_modules/ux-frame/libs/use/useSocket.uts:172") as UTSJSONObject;
                var heartBeat = data.getBoolean("heartBeat");
                if (heartBeat != null && heartBeat) {
                } else {
                    this.onMsg?.invoke(res.data);
                }
            } else {
                this.onMsg?.invoke(res.data);
            }
        }
        );
    }
    private fun closeSocket() {
        this.isConnected = false;
        if (this.socket != null) {
            this.socket!!.close(CloseSocketOptions(reason = "colse"));
            this.socket = null;
        }
    }
    private fun heartBeat() {
        this.clearTimer();
        this.timer = setInterval(fun(){
            this.send(object : UTSJSONObject() {
                var id: Number = 0
                var msg = "HeartBeat"
            });
        }
        , 15 * 1000);
    }
    private fun clearTimer() {
        if (this.timer != null) {
            clearInterval(this.timer as Number);
            this.timer = null;
        }
        if (this.reconnectTimer != null) {
            clearInterval(this.reconnectTimer as Number);
            this.reconnectTimer = null;
        }
    }
    private fun isJson(str: Any): Boolean {
        if (UTSAndroid.`typeof`( str) == "string") {
            try {
                var obj = UTSAndroid.consoleDebugError(JSON.parse(str as String), " at uni_modules/ux-frame/libs/use/useSocket.uts:225");
                if (UTSAndroid.`typeof`( obj) == "object") {
                    return true;
                } else {
                    return false;
                }
            }
             catch (_: Throwable) {
                return false;
            }
        }
        return false;
    }
}
open class Core : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("Core", "uni_modules/ux-frame/libs/core/core.uts", 21, 14);
    }
    open var debugLog: Boolean = true;
    open var unit: String = "px";
    open var theme = uni.UNIB7338A2.theme;
    open var Color = useColor(this);
    open var Date = useDate(this);
    open var Canvas = useCanvas(this);
    open var Socket = uni.UNIB7338A2.Socket(this);
    open var Fmt = useFmt(this);
    open var Obj = useObj(this);
    open var Random = useRandom(this);
    open var Verify = useVerify(this);
    open var Util = useUtil(this);
    constructor(){
        SetTheme(uni.UNIB7338A2.theme, null);
        if (this.debugLog) {
        }
    }
    open fun setThemes(conf: UxTheme) {
        SetTheme(uni.UNIB7338A2.theme, conf);
    }
}
open class UxTab (
    open var id: String? = null,
    @JsonNotNull
    open var name: String,
    open var selectedIcon: String? = null,
    open var unselectedIcon: String? = null,
    open var selectedColor: String? = null,
    open var unselectedColor: String? = null,
    open var badge: Number? = null,
    open var reddot: Boolean? = null,
    open var btn: Boolean? = null,
    open var index: Number? = null,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("UxTab", "uni_modules/ux-frame/libs/types/types.uts", 19, 13)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return UxTabReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class UxTabReactiveObject : UxTab, IUTSReactive<UxTab> {
    override var __v_raw: UxTab;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: UxTab, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(id = __v_raw.id, name = __v_raw.name, selectedIcon = __v_raw.selectedIcon, unselectedIcon = __v_raw.unselectedIcon, selectedColor = __v_raw.selectedColor, unselectedColor = __v_raw.unselectedColor, badge = __v_raw.badge, reddot = __v_raw.reddot, btn = __v_raw.btn, index = __v_raw.index) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UxTabReactiveObject {
        return UxTabReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var id: String?
        get() {
            return trackReactiveGet(__v_raw, "id", __v_raw.id, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("id")) {
                return;
            }
            val oldValue = __v_raw.id;
            __v_raw.id = value;
            triggerReactiveSet(__v_raw, "id", oldValue, value);
        }
    override var name: String
        get() {
            return trackReactiveGet(__v_raw, "name", __v_raw.name, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("name")) {
                return;
            }
            val oldValue = __v_raw.name;
            __v_raw.name = value;
            triggerReactiveSet(__v_raw, "name", oldValue, value);
        }
    override var selectedIcon: String?
        get() {
            return trackReactiveGet(__v_raw, "selectedIcon", __v_raw.selectedIcon, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("selectedIcon")) {
                return;
            }
            val oldValue = __v_raw.selectedIcon;
            __v_raw.selectedIcon = value;
            triggerReactiveSet(__v_raw, "selectedIcon", oldValue, value);
        }
    override var unselectedIcon: String?
        get() {
            return trackReactiveGet(__v_raw, "unselectedIcon", __v_raw.unselectedIcon, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("unselectedIcon")) {
                return;
            }
            val oldValue = __v_raw.unselectedIcon;
            __v_raw.unselectedIcon = value;
            triggerReactiveSet(__v_raw, "unselectedIcon", oldValue, value);
        }
    override var selectedColor: String?
        get() {
            return trackReactiveGet(__v_raw, "selectedColor", __v_raw.selectedColor, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("selectedColor")) {
                return;
            }
            val oldValue = __v_raw.selectedColor;
            __v_raw.selectedColor = value;
            triggerReactiveSet(__v_raw, "selectedColor", oldValue, value);
        }
    override var unselectedColor: String?
        get() {
            return trackReactiveGet(__v_raw, "unselectedColor", __v_raw.unselectedColor, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("unselectedColor")) {
                return;
            }
            val oldValue = __v_raw.unselectedColor;
            __v_raw.unselectedColor = value;
            triggerReactiveSet(__v_raw, "unselectedColor", oldValue, value);
        }
    override var badge: Number?
        get() {
            return trackReactiveGet(__v_raw, "badge", __v_raw.badge, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("badge")) {
                return;
            }
            val oldValue = __v_raw.badge;
            __v_raw.badge = value;
            triggerReactiveSet(__v_raw, "badge", oldValue, value);
        }
    override var reddot: Boolean?
        get() {
            return trackReactiveGet(__v_raw, "reddot", __v_raw.reddot, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("reddot")) {
                return;
            }
            val oldValue = __v_raw.reddot;
            __v_raw.reddot = value;
            triggerReactiveSet(__v_raw, "reddot", oldValue, value);
        }
    override var btn: Boolean?
        get() {
            return trackReactiveGet(__v_raw, "btn", __v_raw.btn, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("btn")) {
                return;
            }
            val oldValue = __v_raw.btn;
            __v_raw.btn = value;
            triggerReactiveSet(__v_raw, "btn", oldValue, value);
        }
    override var index: Number?
        get() {
            return trackReactiveGet(__v_raw, "index", __v_raw.index, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("index")) {
                return;
            }
            val oldValue = __v_raw.index;
            __v_raw.index = value;
            triggerReactiveSet(__v_raw, "index", oldValue, value);
        }
}
open class UXPickerData (
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var value: Any,
    open var children: UTSArray<UXPickerData>? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("UXPickerData", "uni_modules/ux-frame/libs/types/types.uts", 130, 13)
    }
}
val `$ux` = Core();
val install = fun(): Core {
    return `$ux`;
}
;
open class IResponse<T> (
    @JsonNotNull
    open var code: Number,
    @JsonNotNull
    open var success: Boolean = false,
    @JsonNotNull
    open var message: String,
    open var data: T? = null,
    @JsonNotNull
    open var expound: Any,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("IResponse", "common/type/http.uts", 1, 13)
    }
}
open class optionBoxType (
    @JsonNotNull
    open var option: String,
    @JsonNotNull
    open var isSelected: Boolean = false,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("optionBoxType", "pages/healthQuestion/healthQuestion.uvue", 28, 7)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return optionBoxTypeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class optionBoxTypeReactiveObject : optionBoxType, IUTSReactive<optionBoxType> {
    override var __v_raw: optionBoxType;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: optionBoxType, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(option = __v_raw.option, isSelected = __v_raw.isSelected) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): optionBoxTypeReactiveObject {
        return optionBoxTypeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var option: String
        get() {
            return trackReactiveGet(__v_raw, "option", __v_raw.option, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("option")) {
                return;
            }
            val oldValue = __v_raw.option;
            __v_raw.option = value;
            triggerReactiveSet(__v_raw, "option", oldValue, value);
        }
    override var isSelected: Boolean
        get() {
            return trackReactiveGet(__v_raw, "isSelected", __v_raw.isSelected, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("isSelected")) {
                return;
            }
            val oldValue = __v_raw.isSelected;
            __v_raw.isSelected = value;
            triggerReactiveSet(__v_raw, "isSelected", oldValue, value);
        }
}
open class listDataType (
    @JsonNotNull
    open var problem: String,
    @JsonNotNull
    open var optionBox: UTSArray<optionBoxType>,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("listDataType", "pages/healthQuestion/healthQuestion.uvue", 32, 7)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return listDataTypeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class listDataTypeReactiveObject : listDataType, IUTSReactive<listDataType> {
    override var __v_raw: listDataType;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: listDataType, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(problem = __v_raw.problem, optionBox = __v_raw.optionBox) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): listDataTypeReactiveObject {
        return listDataTypeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var problem: String
        get() {
            return trackReactiveGet(__v_raw, "problem", __v_raw.problem, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("problem")) {
                return;
            }
            val oldValue = __v_raw.problem;
            __v_raw.problem = value;
            triggerReactiveSet(__v_raw, "problem", oldValue, value);
        }
    override var optionBox: UTSArray<optionBoxType>
        get() {
            return trackReactiveGet(__v_raw, "optionBox", __v_raw.optionBox, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("optionBox")) {
                return;
            }
            val oldValue = __v_raw.optionBox;
            __v_raw.optionBox = value;
            triggerReactiveSet(__v_raw, "optionBox", oldValue, value);
        }
}
open class resType (
    @JsonNotNull
    open var total: Number,
    @JsonNotNull
    open var recoreds: UTSArray<listDataType>,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("resType", "pages/healthQuestion/healthQuestion.uvue", 36, 7)
    }
}
val GenPagesHealthQuestionHealthQuestionClass = CreateVueComponent(GenPagesHealthQuestionHealthQuestion::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesHealthQuestionHealthQuestion.inheritAttrs, inject = GenPagesHealthQuestionHealthQuestion.inject, props = GenPagesHealthQuestionHealthQuestion.props, propsNeedCastKeys = GenPagesHealthQuestionHealthQuestion.propsNeedCastKeys, emits = GenPagesHealthQuestionHealthQuestion.emits, components = GenPagesHealthQuestionHealthQuestion.components, styles = GenPagesHealthQuestionHealthQuestion.styles);
}
, fun(instance): GenPagesHealthQuestionHealthQuestion {
    return GenPagesHealthQuestionHealthQuestion(instance);
}
);
open class plantListType (
    @JsonNotNull
    open var imgUrl: String,
    @JsonNotNull
    open var isUnlock: Boolean = false,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("plantListType", "components/showWindow/showWindow.uvue", 19, 7)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return plantListTypeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class plantListTypeReactiveObject : plantListType, IUTSReactive<plantListType> {
    override var __v_raw: plantListType;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: plantListType, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(imgUrl = __v_raw.imgUrl, isUnlock = __v_raw.isUnlock) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): plantListTypeReactiveObject {
        return plantListTypeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var imgUrl: String
        get() {
            return trackReactiveGet(__v_raw, "imgUrl", __v_raw.imgUrl, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("imgUrl")) {
                return;
            }
            val oldValue = __v_raw.imgUrl;
            __v_raw.imgUrl = value;
            triggerReactiveSet(__v_raw, "imgUrl", oldValue, value);
        }
    override var isUnlock: Boolean
        get() {
            return trackReactiveGet(__v_raw, "isUnlock", __v_raw.isUnlock, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("isUnlock")) {
                return;
            }
            val oldValue = __v_raw.isUnlock;
            __v_raw.isUnlock = value;
            triggerReactiveSet(__v_raw, "isUnlock", oldValue, value);
        }
}
val GenComponentsShowWindowShowWindowClass = CreateVueComponent(GenComponentsShowWindowShowWindow::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsShowWindowShowWindow.inheritAttrs, inject = GenComponentsShowWindowShowWindow.inject, props = GenComponentsShowWindowShowWindow.props, propsNeedCastKeys = GenComponentsShowWindowShowWindow.propsNeedCastKeys, emits = GenComponentsShowWindowShowWindow.emits, components = GenComponentsShowWindowShowWindow.components, styles = GenComponentsShowWindowShowWindow.styles);
}
, fun(instance): GenComponentsShowWindowShowWindow {
    return GenComponentsShowWindowShowWindow(instance);
}
);
val GenComponentsCommunityCommunityClass = CreateVueComponent(GenComponentsCommunityCommunity::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsCommunityCommunity.inheritAttrs, inject = GenComponentsCommunityCommunity.inject, props = GenComponentsCommunityCommunity.props, propsNeedCastKeys = GenComponentsCommunityCommunity.propsNeedCastKeys, emits = GenComponentsCommunityCommunity.emits, components = GenComponentsCommunityCommunity.components, styles = GenComponentsCommunityCommunity.styles);
}
, fun(instance): GenComponentsCommunityCommunity {
    return GenComponentsCommunityCommunity(instance);
}
);
open class functionListType (
    @JsonNotNull
    open var icon: String,
    @JsonNotNull
    open var navgiateUrl: String,
    @JsonNotNull
    open var text: String,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("functionListType", "components/home/home.uvue", 116, 7)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return functionListTypeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class functionListTypeReactiveObject : functionListType, IUTSReactive<functionListType> {
    override var __v_raw: functionListType;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: functionListType, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(icon = __v_raw.icon, navgiateUrl = __v_raw.navgiateUrl, text = __v_raw.text) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): functionListTypeReactiveObject {
        return functionListTypeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var icon: String
        get() {
            return trackReactiveGet(__v_raw, "icon", __v_raw.icon, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("icon")) {
                return;
            }
            val oldValue = __v_raw.icon;
            __v_raw.icon = value;
            triggerReactiveSet(__v_raw, "icon", oldValue, value);
        }
    override var navgiateUrl: String
        get() {
            return trackReactiveGet(__v_raw, "navgiateUrl", __v_raw.navgiateUrl, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("navgiateUrl")) {
                return;
            }
            val oldValue = __v_raw.navgiateUrl;
            __v_raw.navgiateUrl = value;
            triggerReactiveSet(__v_raw, "navgiateUrl", oldValue, value);
        }
    override var text: String
        get() {
            return trackReactiveGet(__v_raw, "text", __v_raw.text, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("text")) {
                return;
            }
            val oldValue = __v_raw.text;
            __v_raw.text = value;
            triggerReactiveSet(__v_raw, "text", oldValue, value);
        }
}
open class signInDataType (
    @JsonNotNull
    open var icon: String,
    @JsonNotNull
    open var text: String,
    @JsonNotNull
    open var isSignIn: Boolean = false,
    @JsonNotNull
    open var currentSignIn: Boolean = false,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("signInDataType", "components/home/home.uvue", 121, 7)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return signInDataTypeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class signInDataTypeReactiveObject : signInDataType, IUTSReactive<signInDataType> {
    override var __v_raw: signInDataType;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: signInDataType, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(icon = __v_raw.icon, text = __v_raw.text, isSignIn = __v_raw.isSignIn, currentSignIn = __v_raw.currentSignIn) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): signInDataTypeReactiveObject {
        return signInDataTypeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var icon: String
        get() {
            return trackReactiveGet(__v_raw, "icon", __v_raw.icon, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("icon")) {
                return;
            }
            val oldValue = __v_raw.icon;
            __v_raw.icon = value;
            triggerReactiveSet(__v_raw, "icon", oldValue, value);
        }
    override var text: String
        get() {
            return trackReactiveGet(__v_raw, "text", __v_raw.text, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("text")) {
                return;
            }
            val oldValue = __v_raw.text;
            __v_raw.text = value;
            triggerReactiveSet(__v_raw, "text", oldValue, value);
        }
    override var isSignIn: Boolean
        get() {
            return trackReactiveGet(__v_raw, "isSignIn", __v_raw.isSignIn, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("isSignIn")) {
                return;
            }
            val oldValue = __v_raw.isSignIn;
            __v_raw.isSignIn = value;
            triggerReactiveSet(__v_raw, "isSignIn", oldValue, value);
        }
    override var currentSignIn: Boolean
        get() {
            return trackReactiveGet(__v_raw, "currentSignIn", __v_raw.currentSignIn, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("currentSignIn")) {
                return;
            }
            val oldValue = __v_raw.currentSignIn;
            __v_raw.currentSignIn = value;
            triggerReactiveSet(__v_raw, "currentSignIn", oldValue, value);
        }
}
open class gandeplantType (
    @JsonNotNull
    open var plant: String,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("gandeplantType", "components/home/home.uvue", 127, 7)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return gandeplantTypeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class gandeplantTypeReactiveObject : gandeplantType, IUTSReactive<gandeplantType> {
    override var __v_raw: gandeplantType;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: gandeplantType, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(plant = __v_raw.plant) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): gandeplantTypeReactiveObject {
        return gandeplantTypeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var plant: String
        get() {
            return trackReactiveGet(__v_raw, "plant", __v_raw.plant, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("plant")) {
                return;
            }
            val oldValue = __v_raw.plant;
            __v_raw.plant = value;
            triggerReactiveSet(__v_raw, "plant", oldValue, value);
        }
}
val GenComponentsHomeHomeClass = CreateVueComponent(GenComponentsHomeHome::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsHomeHome.inheritAttrs, inject = GenComponentsHomeHome.inject, props = GenComponentsHomeHome.props, propsNeedCastKeys = GenComponentsHomeHome.propsNeedCastKeys, emits = GenComponentsHomeHome.emits, components = GenComponentsHomeHome.components, styles = GenComponentsHomeHome.styles);
}
, fun(instance): GenComponentsHomeHome {
    return GenComponentsHomeHome(instance);
}
);
val GenComponentsHealthComponentHealthComponentClass = CreateVueComponent(GenComponentsHealthComponentHealthComponent::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsHealthComponentHealthComponent.inheritAttrs, inject = GenComponentsHealthComponentHealthComponent.inject, props = GenComponentsHealthComponentHealthComponent.props, propsNeedCastKeys = GenComponentsHealthComponentHealthComponent.propsNeedCastKeys, emits = GenComponentsHealthComponentHealthComponent.emits, components = GenComponentsHealthComponentHealthComponent.components, styles = GenComponentsHealthComponentHealthComponent.styles);
}
, fun(instance): GenComponentsHealthComponentHealthComponent {
    return GenComponentsHealthComponentHealthComponent(instance);
}
);
open class tagType (
    @JsonNotNull
    open var title: String,
    @JsonNotNull
    open var count: Number,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("tagType", "components/myPage/myPage.uvue", 84, 7)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return tagTypeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class tagTypeReactiveObject : tagType, IUTSReactive<tagType> {
    override var __v_raw: tagType;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: tagType, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(title = __v_raw.title, count = __v_raw.count) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): tagTypeReactiveObject {
        return tagTypeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var title: String
        get() {
            return trackReactiveGet(__v_raw, "title", __v_raw.title, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("title")) {
                return;
            }
            val oldValue = __v_raw.title;
            __v_raw.title = value;
            triggerReactiveSet(__v_raw, "title", oldValue, value);
        }
    override var count: Number
        get() {
            return trackReactiveGet(__v_raw, "count", __v_raw.count, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("count")) {
                return;
            }
            val oldValue = __v_raw.count;
            __v_raw.count = value;
            triggerReactiveSet(__v_raw, "count", oldValue, value);
        }
}
open class planType (
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var color: String,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("planType", "components/myPage/myPage.uvue", 88, 7)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return planTypeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class planTypeReactiveObject : planType, IUTSReactive<planType> {
    override var __v_raw: planType;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: planType, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(name = __v_raw.name, color = __v_raw.color) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): planTypeReactiveObject {
        return planTypeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var name: String
        get() {
            return trackReactiveGet(__v_raw, "name", __v_raw.name, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("name")) {
                return;
            }
            val oldValue = __v_raw.name;
            __v_raw.name = value;
            triggerReactiveSet(__v_raw, "name", oldValue, value);
        }
    override var color: String
        get() {
            return trackReactiveGet(__v_raw, "color", __v_raw.color, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("color")) {
                return;
            }
            val oldValue = __v_raw.color;
            __v_raw.color = value;
            triggerReactiveSet(__v_raw, "color", oldValue, value);
        }
}
val GenComponentsMyPageMyPageClass = CreateVueComponent(GenComponentsMyPageMyPage::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsMyPageMyPage.inheritAttrs, inject = GenComponentsMyPageMyPage.inject, props = GenComponentsMyPageMyPage.props, propsNeedCastKeys = GenComponentsMyPageMyPage.propsNeedCastKeys, emits = GenComponentsMyPageMyPage.emits, components = GenComponentsMyPageMyPage.components, styles = GenComponentsMyPageMyPage.styles);
}
, fun(instance): GenComponentsMyPageMyPage {
    return GenComponentsMyPageMyPage(instance);
}
);
val GenPagesTabbarTabbarClass = CreateVueComponent(GenPagesTabbarTabbar::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesTabbarTabbar.inheritAttrs, inject = GenPagesTabbarTabbar.inject, props = GenPagesTabbarTabbar.props, propsNeedCastKeys = GenPagesTabbarTabbar.propsNeedCastKeys, emits = GenPagesTabbarTabbar.emits, components = GenPagesTabbarTabbar.components, styles = GenPagesTabbarTabbar.styles);
}
, fun(instance): GenPagesTabbarTabbar {
    return GenPagesTabbarTabbar(instance);
}
);
open class TloginInfo (
    @JsonNotNull
    open var userId: String,
    @JsonNotNull
    open var password: String,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("TloginInfo", "common/type/user.uts", 20, 13)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return TloginInfoReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class TloginInfoReactiveObject : TloginInfo, IUTSReactive<TloginInfo> {
    override var __v_raw: TloginInfo;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: TloginInfo, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(userId = __v_raw.userId, password = __v_raw.password) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): TloginInfoReactiveObject {
        return TloginInfoReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var userId: String
        get() {
            return trackReactiveGet(__v_raw, "userId", __v_raw.userId, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("userId")) {
                return;
            }
            val oldValue = __v_raw.userId;
            __v_raw.userId = value;
            triggerReactiveSet(__v_raw, "userId", oldValue, value);
        }
    override var password: String
        get() {
            return trackReactiveGet(__v_raw, "password", __v_raw.password, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("password")) {
                return;
            }
            val oldValue = __v_raw.password;
            __v_raw.password = value;
            triggerReactiveSet(__v_raw, "password", oldValue, value);
        }
}
open class IToken (
    @JsonNotNull
    open var token: String,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("IToken", "common/type/user.uts", 24, 13)
    }
}
open class ICode (
    @JsonNotNull
    open var code: String,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("ICode", "common/type/user.uts", 27, 13)
    }
}
val GenPagesLoginLoginClass = CreateVueComponent(GenPagesLoginLogin::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesLoginLogin.inheritAttrs, inject = GenPagesLoginLogin.inject, props = GenPagesLoginLogin.props, propsNeedCastKeys = GenPagesLoginLogin.propsNeedCastKeys, emits = GenPagesLoginLogin.emits, components = GenPagesLoginLogin.components, styles = GenPagesLoginLogin.styles);
}
, fun(instance): GenPagesLoginLogin {
    return GenPagesLoginLogin(instance);
}
);
val GenPagesPlantDetailPlantDetailClass = CreateVueComponent(GenPagesPlantDetailPlantDetail::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesPlantDetailPlantDetail.inheritAttrs, inject = GenPagesPlantDetailPlantDetail.inject, props = GenPagesPlantDetailPlantDetail.props, propsNeedCastKeys = GenPagesPlantDetailPlantDetail.propsNeedCastKeys, emits = GenPagesPlantDetailPlantDetail.emits, components = GenPagesPlantDetailPlantDetail.components, styles = GenPagesPlantDetailPlantDetail.styles);
}
, fun(instance): GenPagesPlantDetailPlantDetail {
    return GenPagesPlantDetailPlantDetail(instance);
}
);
val GenPagesHealthDetailHealthDetailClass = CreateVueComponent(GenPagesHealthDetailHealthDetail::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesHealthDetailHealthDetail.inheritAttrs, inject = GenPagesHealthDetailHealthDetail.inject, props = GenPagesHealthDetailHealthDetail.props, propsNeedCastKeys = GenPagesHealthDetailHealthDetail.propsNeedCastKeys, emits = GenPagesHealthDetailHealthDetail.emits, components = GenPagesHealthDetailHealthDetail.components, styles = GenPagesHealthDetailHealthDetail.styles);
}
, fun(instance): GenPagesHealthDetailHealthDetail {
    return GenPagesHealthDetailHealthDetail(instance);
}
);
val GenPagesValidateCodeValidateCodeClass = CreateVueComponent(GenPagesValidateCodeValidateCode::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesValidateCodeValidateCode.inheritAttrs, inject = GenPagesValidateCodeValidateCode.inject, props = GenPagesValidateCodeValidateCode.props, propsNeedCastKeys = GenPagesValidateCodeValidateCode.propsNeedCastKeys, emits = GenPagesValidateCodeValidateCode.emits, components = GenPagesValidateCodeValidateCode.components, styles = GenPagesValidateCodeValidateCode.styles);
}
, fun(instance): GenPagesValidateCodeValidateCode {
    return GenPagesValidateCodeValidateCode(instance);
}
);
open class registerLoginInfoType (
    @JsonNotNull
    open var tel: String,
    @JsonNotNull
    open var password: String,
    @JsonNotNull
    open var code: String,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("registerLoginInfoType", "pages/register/register.uvue", 22, 7)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return registerLoginInfoTypeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class registerLoginInfoTypeReactiveObject : registerLoginInfoType, IUTSReactive<registerLoginInfoType> {
    override var __v_raw: registerLoginInfoType;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: registerLoginInfoType, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(tel = __v_raw.tel, password = __v_raw.password, code = __v_raw.code) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): registerLoginInfoTypeReactiveObject {
        return registerLoginInfoTypeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var tel: String
        get() {
            return trackReactiveGet(__v_raw, "tel", __v_raw.tel, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("tel")) {
                return;
            }
            val oldValue = __v_raw.tel;
            __v_raw.tel = value;
            triggerReactiveSet(__v_raw, "tel", oldValue, value);
        }
    override var password: String
        get() {
            return trackReactiveGet(__v_raw, "password", __v_raw.password, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("password")) {
                return;
            }
            val oldValue = __v_raw.password;
            __v_raw.password = value;
            triggerReactiveSet(__v_raw, "password", oldValue, value);
        }
    override var code: String
        get() {
            return trackReactiveGet(__v_raw, "code", __v_raw.code, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("code")) {
                return;
            }
            val oldValue = __v_raw.code;
            __v_raw.code = value;
            triggerReactiveSet(__v_raw, "code", oldValue, value);
        }
}
val GenPagesRegisterRegisterClass = CreateVueComponent(GenPagesRegisterRegister::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesRegisterRegister.inheritAttrs, inject = GenPagesRegisterRegister.inject, props = GenPagesRegisterRegister.props, propsNeedCastKeys = GenPagesRegisterRegister.propsNeedCastKeys, emits = GenPagesRegisterRegister.emits, components = GenPagesRegisterRegister.components, styles = GenPagesRegisterRegister.styles);
}
, fun(instance): GenPagesRegisterRegister {
    return GenPagesRegisterRegister(instance);
}
);
open class chartType (
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var area: Number,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("chartType", "pages/resultHealth/resultHealth.uvue", 72, 7)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return chartTypeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class chartTypeReactiveObject : chartType, IUTSReactive<chartType> {
    override var __v_raw: chartType;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: chartType, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(name = __v_raw.name, area = __v_raw.area) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): chartTypeReactiveObject {
        return chartTypeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var name: String
        get() {
            return trackReactiveGet(__v_raw, "name", __v_raw.name, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("name")) {
                return;
            }
            val oldValue = __v_raw.name;
            __v_raw.name = value;
            triggerReactiveSet(__v_raw, "name", oldValue, value);
        }
    override var area: Number
        get() {
            return trackReactiveGet(__v_raw, "area", __v_raw.area, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("area")) {
                return;
            }
            val oldValue = __v_raw.area;
            __v_raw.area = value;
            triggerReactiveSet(__v_raw, "area", oldValue, value);
        }
}
val GenPagesResultHealthResultHealthClass = CreateVueComponent(GenPagesResultHealthResultHealth::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesResultHealthResultHealth.inheritAttrs, inject = GenPagesResultHealthResultHealth.inject, props = GenPagesResultHealthResultHealth.props, propsNeedCastKeys = GenPagesResultHealthResultHealth.propsNeedCastKeys, emits = GenPagesResultHealthResultHealth.emits, components = GenPagesResultHealthResultHealth.components, styles = GenPagesResultHealthResultHealth.styles);
}
, fun(instance): GenPagesResultHealthResultHealth {
    return GenPagesResultHealthResultHealth(instance);
}
);
open class planType1 (
    @JsonNotNull
    open var id: String,
    open var plantId: String? = null,
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var subarea: String,
    @JsonNotNull
    open var punchCycle: String,
    @JsonNotNull
    open var punchSize: Number,
    @JsonNotNull
    open var remindTime: String,
    @JsonNotNull
    open var remindMusic: String,
    @JsonNotNull
    open var automatic: String,
    open var startTime: String? = null,
    open var endTime: String? = null,
    @JsonNotNull
    open var isSelected: Boolean = false,
) : UTSReactiveObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("planType", "pages/drawPlan/drawPlan.uvue", 43, 7)
    }
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return planType1ReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
open class planType1ReactiveObject : planType1, IUTSReactive<planType1> {
    override var __v_raw: planType1;
    override var __v_isReadonly: Boolean;
    override var __v_isShallow: Boolean;
    override var __v_skip: Boolean;
    constructor(__v_raw: planType1, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(id = __v_raw.id, plantId = __v_raw.plantId, name = __v_raw.name, subarea = __v_raw.subarea, punchCycle = __v_raw.punchCycle, punchSize = __v_raw.punchSize, remindTime = __v_raw.remindTime, remindMusic = __v_raw.remindMusic, automatic = __v_raw.automatic, startTime = __v_raw.startTime, endTime = __v_raw.endTime, isSelected = __v_raw.isSelected) {
        this.__v_raw = __v_raw;
        this.__v_isReadonly = __v_isReadonly;
        this.__v_isShallow = __v_isShallow;
        this.__v_skip = __v_skip;
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): planType1ReactiveObject {
        return planType1ReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip);
    }
    override var id: String
        get() {
            return trackReactiveGet(__v_raw, "id", __v_raw.id, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("id")) {
                return;
            }
            val oldValue = __v_raw.id;
            __v_raw.id = value;
            triggerReactiveSet(__v_raw, "id", oldValue, value);
        }
    override var plantId: String?
        get() {
            return trackReactiveGet(__v_raw, "plantId", __v_raw.plantId, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("plantId")) {
                return;
            }
            val oldValue = __v_raw.plantId;
            __v_raw.plantId = value;
            triggerReactiveSet(__v_raw, "plantId", oldValue, value);
        }
    override var name: String
        get() {
            return trackReactiveGet(__v_raw, "name", __v_raw.name, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("name")) {
                return;
            }
            val oldValue = __v_raw.name;
            __v_raw.name = value;
            triggerReactiveSet(__v_raw, "name", oldValue, value);
        }
    override var subarea: String
        get() {
            return trackReactiveGet(__v_raw, "subarea", __v_raw.subarea, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("subarea")) {
                return;
            }
            val oldValue = __v_raw.subarea;
            __v_raw.subarea = value;
            triggerReactiveSet(__v_raw, "subarea", oldValue, value);
        }
    override var punchCycle: String
        get() {
            return trackReactiveGet(__v_raw, "punchCycle", __v_raw.punchCycle, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("punchCycle")) {
                return;
            }
            val oldValue = __v_raw.punchCycle;
            __v_raw.punchCycle = value;
            triggerReactiveSet(__v_raw, "punchCycle", oldValue, value);
        }
    override var punchSize: Number
        get() {
            return trackReactiveGet(__v_raw, "punchSize", __v_raw.punchSize, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("punchSize")) {
                return;
            }
            val oldValue = __v_raw.punchSize;
            __v_raw.punchSize = value;
            triggerReactiveSet(__v_raw, "punchSize", oldValue, value);
        }
    override var remindTime: String
        get() {
            return trackReactiveGet(__v_raw, "remindTime", __v_raw.remindTime, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("remindTime")) {
                return;
            }
            val oldValue = __v_raw.remindTime;
            __v_raw.remindTime = value;
            triggerReactiveSet(__v_raw, "remindTime", oldValue, value);
        }
    override var remindMusic: String
        get() {
            return trackReactiveGet(__v_raw, "remindMusic", __v_raw.remindMusic, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("remindMusic")) {
                return;
            }
            val oldValue = __v_raw.remindMusic;
            __v_raw.remindMusic = value;
            triggerReactiveSet(__v_raw, "remindMusic", oldValue, value);
        }
    override var automatic: String
        get() {
            return trackReactiveGet(__v_raw, "automatic", __v_raw.automatic, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("automatic")) {
                return;
            }
            val oldValue = __v_raw.automatic;
            __v_raw.automatic = value;
            triggerReactiveSet(__v_raw, "automatic", oldValue, value);
        }
    override var startTime: String?
        get() {
            return trackReactiveGet(__v_raw, "startTime", __v_raw.startTime, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("startTime")) {
                return;
            }
            val oldValue = __v_raw.startTime;
            __v_raw.startTime = value;
            triggerReactiveSet(__v_raw, "startTime", oldValue, value);
        }
    override var endTime: String?
        get() {
            return trackReactiveGet(__v_raw, "endTime", __v_raw.endTime, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("endTime")) {
                return;
            }
            val oldValue = __v_raw.endTime;
            __v_raw.endTime = value;
            triggerReactiveSet(__v_raw, "endTime", oldValue, value);
        }
    override var isSelected: Boolean
        get() {
            return trackReactiveGet(__v_raw, "isSelected", __v_raw.isSelected, this.__v_isReadonly, this.__v_isShallow);
        }
        set(value) {
            if (!this.__v_canSet("isSelected")) {
                return;
            }
            val oldValue = __v_raw.isSelected;
            __v_raw.isSelected = value;
            triggerReactiveSet(__v_raw, "isSelected", oldValue, value);
        }
}
val GenPagesDrawPlanDrawPlanClass = CreateVueComponent(GenPagesDrawPlanDrawPlan::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesDrawPlanDrawPlan.inheritAttrs, inject = GenPagesDrawPlanDrawPlan.inject, props = GenPagesDrawPlanDrawPlan.props, propsNeedCastKeys = GenPagesDrawPlanDrawPlan.propsNeedCastKeys, emits = GenPagesDrawPlanDrawPlan.emits, components = GenPagesDrawPlanDrawPlan.components, styles = GenPagesDrawPlanDrawPlan.styles);
}
, fun(instance): GenPagesDrawPlanDrawPlan {
    return GenPagesDrawPlanDrawPlan(instance);
}
);
fun createApp(): UTSJSONObject {
    val app = createSSRApp(GenAppClass);
    app.config.globalProperties["\$ux"] = true;
    return object : UTSJSONObject() {
        var app = app
    };
}
fun main(app: IApp) {
    definePageRoutes();
    defineAppConfig();
    (createApp()["app"] as VueApp).mount(app);
}
open class UniAppConfig : AppConfig {
    override var name: String = "轻养问康";
    override var appid: String = "__UNI__B7338A2";
    override var versionName: String = "1.0.0";
    override var versionCode: String = "1";
    override var uniCompileVersion: String = "4.13";
    constructor(){}
}
fun definePageRoutes() {
    __uniRoutes.push(PageRoute(path = "pages/healthQuestion/healthQuestion", component = GenPagesHealthQuestionHealthQuestionClass, meta = PageMeta(isQuit = true), style = utsMapOf("navigationBarTitleText" to "")));
    __uniRoutes.push(PageRoute(path = "pages/tabbar/tabbar", component = GenPagesTabbarTabbarClass, meta = PageMeta(isQuit = false), style = utsMapOf("navigationBarTitleText" to "")));
    __uniRoutes.push(PageRoute(path = "pages/login/login", component = GenPagesLoginLoginClass, meta = PageMeta(isQuit = false), style = utsMapOf("navigationBarTitleText" to "")));
    __uniRoutes.push(PageRoute(path = "pages/plantDetail/plantDetail", component = GenPagesPlantDetailPlantDetailClass, meta = PageMeta(isQuit = false), style = utsMapOf("navigationBarTitleText" to "")));
    __uniRoutes.push(PageRoute(path = "pages/healthDetail/healthDetail", component = GenPagesHealthDetailHealthDetailClass, meta = PageMeta(isQuit = false), style = utsMapOf("navigationBarTitleText" to "每日药膳推荐")));
    __uniRoutes.push(PageRoute(path = "pages/validateCode/validateCode", component = GenPagesValidateCodeValidateCodeClass, meta = PageMeta(isQuit = false), style = utsMapOf("navigationBarTitleText" to "")));
    __uniRoutes.push(PageRoute(path = "pages/register/register", component = GenPagesRegisterRegisterClass, meta = PageMeta(isQuit = false), style = utsMapOf("navigationBarTitleText" to "")));
    __uniRoutes.push(PageRoute(path = "pages/resultHealth/resultHealth", component = GenPagesResultHealthResultHealthClass, meta = PageMeta(isQuit = false), style = utsMapOf("navigationBarTitleText" to "")));
    __uniRoutes.push(PageRoute(path = "pages/drawPlan/drawPlan", component = GenPagesDrawPlanDrawPlanClass, meta = PageMeta(isQuit = false), style = utsMapOf("navigationBarTitleText" to "")));
}
val __uniTabBar: Map<String, Any?>? = utsMapOf();
val __uniLaunchPage: Map<String, Any?> = utsMapOf("url" to "pages/healthQuestion/healthQuestion", "style" to utsMapOf("navigationBarTitleText" to ""));
@Suppress("UNCHECKED_CAST")
fun defineAppConfig() {
    __uniConfig.entryPagePath = "/pages/healthQuestion/healthQuestion";
    __uniConfig.globalStyle = utsMapOf("navigationBarTextStyle" to "black", "navigationBarTitleText" to "uni-app x", "navigationBarBackgroundColor" to "#F8F8F8", "backgroundColor" to "#F8F8F8", "navigationStyle" to "custom");
    __uniConfig.tabBar = __uniTabBar as Map<String, Any>?;
    __uniConfig.conditionUrl = "";
    __uniConfig.uniIdRouter = utsMapOf();
    __uniConfig.ready = true;
}
var `___$ux` = install();
var VueComponent.`$ux`
    get() = `___$ux`
    set(value) {
        `___$ux` = value;
    }
fun getApp(): GenApp {
    return getBaseApp() as GenApp;
}
