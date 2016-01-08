### Overview

Viblast PDN Android SDK extends your peer-assisted video delivery to the part of your audience using Android devices. Viblast PDN allows you to stream to web and mobile audiences up to 70% more efficiently.

Visit Viblast PDN's [web page](http://viblast.com/pdn/)

### Project setup
Let's assume you have an existing Eclipse project or you've just created one.

Download the provided `viblast-<domain>-<version>.android.zip` file and
extract it into the directory of your project

```unzip viblast-<domain>-<version>.android.zip -d <path-to-your-android-project>```

After that you should have a `viblast.jar` and `exoplayer.jar` file in your project `libs/`
directory and two file called `libviblast.so` and `libnative-viblast.jni.so`
in the `libs/armeabi/` and `libs/x86/` directory.

### Basic usage example

Viblast requires Internet access in order to work, so make sure you have
requested the appropriate permissions in your `AndroidManifest.xml` file

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android" >
	...
	<uses-permission android:name="android.permission.INTERNET" />
	...
</manifest>
```

Create a `ViblastView` inside your layout

```xml
...
<com.viblast.android.ViblastView
		android:id="@+id/viblast_view"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:layout_gravity="center" />
...
```

Naturally, you can specify the parameters most suitable for your
application's interface. The code above is just an example.

The only thing you need is a ViblastPlayer instance. A good spot to
create it is in the Activity's onCreate method.

```java
public class MainActivity extends Activity {
	... // as usual
    private ViblastPlayer viblastPlayer;
    private ViblastView viblastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		... // as usual

		viblastView = (ViblastView)findViewById(R.id.viblast_view);

		final ViblastConfig vbConfig = new ViblastConfig();
		vbConfig.setCdnStream("your-cdn-stream");
		vbConfig.advancedConfig.put("enable-pdn", "true");
		vbConfig.advancedConfig.put("enable-realtime-loggger", "true");
		vbConfig.advancedConfig.put("realtime-logger-server", "wss://cs.viblast.com/rt");

		viblastPlayer = new ViblastPlayer(viblastView, vbConfig);
    }
```

Start Viblast on Activity start

```java
@Override
protected void onStart() {
	super.onStart();
	viblastPlayer.start();
}
```

Don't forget to stop Viblast when the Activity stops

```java
@Override
protected void onStop() {
	viblastPlayer.stop();
	super.onStop();
}
```

That's about it. Your video playback through Viblast should begin after
you start your Activity.
