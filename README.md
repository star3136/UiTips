# UiTips

## UiTips的功能介绍
UiTips旨在统一app提示，保持全局风格一致。主要特点如下：
- 绑定了Activity、Fragment生命周期
- 每个绑定的Activity、Fragment中，只有一个同种类的View或者Dialog显示，显示位置居中
- 扩展性强


## UiTips简单使用
### 1. View类型的loading
View类型的loading的样式比较简单和固定，通常可以应对大多数情况。
```java
//显示
UiTips.with(this)        //Activity或者Fragment
        .loadingView()
        .content("正在加载...")  //设置加载时的消息内容
        .show(parent);     //显示时必须指定父View

//隐藏
UiTips.with(this)
        .loadingView()
        .hide();

//当前的loading是否正在显示
UiTips.with(this)
        .loadingView()
        .isShowing();
```



### 2.View类型错误提示
目前的错误提示有如下类型，网络、服务端返回空数据、页面不存在、搜索无结果。每一个类型其实只是对应一张全局统一的提示图片，如果有少见的提示图片，也可以设置图片资源id，一般情况下，推荐使用接口中定义的错误提示类型，这样比较好统一风格。
```java
public interface IWarnView {
    /**
     * 支持的错误类型
     */
    int NETWORK = 0;  //网络错误
    int EMPTY_DATA = 1;  //没有数据
    int NOT_FOUND = 2; //页面不存在
    int SEARCH_EMPTY = 3; //搜索无结果

    int UNKNOWN = 999;  //未知错误
    int CUSTOM = 1000;  //使用指定的图片
    ...
}

//显示
UiTips.with(this)
        .warnView()
        .type(IWarnView.NETWORK)  //网络类型的错误
        .content("网络已断开")
        .show(parent);     //显示时必须指定父View
//使用指定图片
UiTips.with(this)
        .warnView()
        .type(IWarnView.CUSTOM)  //设置为CUSTOM，指定的图片才会生效
        .warnImage(imgRes)    //图片的资源id
        .content("这是一个不常见的错误")
        .show(parent);       //显示时必须指定父View
//隐藏
UiTips.with(this)
        .warnView()
        .hide();

//当前的提示View是否正在显示
UiTips.with(this)
        .warnView()
        .isShowing();
```

### Dialog类型的loading
```java
//显示
UiTips.with(this)            //Activity或Fragment
        .loadingDialog()
        .content("玩命加载中...")   //设置加载时的消息内容
        .cancelable(false)      //设置是否可以取消
        .show();

//隐藏
UiTips.with(this)
        .loadingDialog()
        .hide();

//当前的Dialog是否正在显示
UiTips.with(this)
        .loadingDialog()
        .isShowing();
```

### 通用Dialog的使用
```java
public interface IWarnDialog {
    /**
     * 对话框的风格
     */
    int SINGLE_BUTTON = 0;
    int TWO_BUTTON = 1;
    ...
}
//显示
UiTips.with(this)            // Activity或Fragment
        .warnDialog()
        .title("温馨提醒")       //设置标题
        .content("您的VIP会员即将到期，请充值，以维持您尊贵的会员身份！")  //设置内容
        .okButtonText("充值")   //设置确定按钮的文本, 默认是 “确定”
        .okButtonClick(...)    //设置确定按钮的点击事件，默认是隐藏Dialog
        .cancelButtonText("不充")  //设置取消按钮的文本， 默认是“取消”
        .cancelButtonClick(...)   //设置取消按钮的点击事件，默认是隐藏Dialog
        .style(IWarnDialog.TWO_BUTTON)  //设置风格，默认是只有一个确定按钮的，如果需要显示两个按钮，需要设置为TWO_BUTTON
        .cancelable(false)    //设置是否可以取消，默认可以取消
        .show();

//最简单的使用，表现为有一个按钮和内容的Dialog，可以取消，点击按钮，隐藏Dialog
UiTips.with(this)
        .warnDialog()
        .content("消息内容")
        .show();

//隐藏
UiTips.with(this)
        .warnDialog()
        .hide();

//当前的Dialog是否正在显示
UiTips.with(this)
        .warnDialog()
        .isShowing();
```

## UiTips配置
### 1.全局配置
```java
//在任何时候都可以通过如下方法来设置全局的定制化需求，UiTips.newConfig()会保留之前的配置
UiTips.newConfig()
        .loadingViewFactory(...)  
        .loadingDialogFactory(...)
        .warnViewFactory(...)
        .warnViewFactory(...)
        .build()
        .set();
```

下面通过一个最简单的例子来自定义一个布局中的loading，其他的基本一样。
```java
//实现一个自定义加载的配置例子
public class MyLoadingView extends BaseLoadingView{
    TextView textView;
    
    @Override
    protected View createLayout(ViewGroup parent) {
        textView = new TextView(parent.getContext());
        return textView;
    }

    @Override
    protected void update() {
        textView.setText(content);
    }
    
    public static class MyLoadingViewFactory implements ILoadingViewFactory{
        @Override
        public ILoadingView create() {
            return new MyLoadingView();
        }
    }
}

//配置
UiTips.newConfig()
        .loadingViewFactory(new MyLoadingView.MyLoadingViewFactory())
        .build()
        .set();
```

### 2.在当前Activity或Fragment中设置自定义View、Dialog
如下代码，通过这些方法设置的自定义View、Dialog都被会UiTips管理，一次设置，在整个Activity、Fragment生命周期内有效。
```java
//设置View类型loading
UiTips.with(this)
        .loadingView()
        .customView(...)   //传入View类型的参数
        .show(parent);

//设置View类型错误提示
UiTips.with(this)
        .warnView()
        .customView(...)  //传入View类型的参数
        .show(parent);

//设置Dialog类型的loading
UiTips.with(this)
        .loadingDialog()
        .customDialog(...)  //传入Dialog类型的参数
        .show();

//设置Dialog类型的提示
UiTips.with(this)
        .warnDialog()
        .customDialog(...)  //传入Dialog类型的参数
        .show();
```

## 使用方法
项目根目录build.gradle添加如下:
```
allprojects {
        repositories {
                ...
                maven { url 'https://jitpack.io' }
        }
}
```

添加依赖:
```
dependencies {
        implementation 'com.github.star3136:UiTips:Tag'
}
```


