# PageStateView


PageStateView with Stepwise movement

![](https://github.com/VIKAS9899/PageStateView/blob/master/images/step.gif?raw=true)


PageStateView with Trans movement

![](https://github.com/VIKAS9899/PageStateView/blob/master/images/trans.gif?raw=true)

## Usage

```xml
<com.vicky.pagestateview.PageStateView
            android:id="@+id/page_state_view"
            android:layout_width="wrap_content"
            android:layout_gravity="bottom|center_horizontal"
            android:layout_height="wrap_content"
            android:layout_margin="20dp"
            app:primary_color="@color/colorPrimary"
            app:secondary_color="@color/colorAccent"
            app:size="10dp"
            app:movement="trans"/>
      />
```


        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        PageStateView pageStateView = findViewById(R.id.page_state_view);
		pageStateView.setupWithViewPager(viewPager);

## Download
Add it in your root build.gradle at the end of repositories:

```javascript
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency

```javascript
dependencies {
	    implementation 'com.github.VIKAS9899:PageStateView:1.0'
}
```

## License

    Copyright 2018 Vikas Sharma

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
