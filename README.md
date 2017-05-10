
添加到你的工程:
Step 1 :

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
Step 2 :

dependencies {
		compile 'com.github.User:Repo:Tag'
	}
  
 
 使用说明：
  控件：
  <com.nonight.timepickerview.view.TimePickerView
        android:id="@+id/timep"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

    </com.nonight.timepickerview.view.TimePickerView>

class ：

TimePickerView timePickerView = (TimePickerView) findViewById(R.id.timep);
timePickerView.setOnValueChangeListener(new TimePickerView.OnValueChangeListener() {
            @Override
            public void onChange(Date date) {
                
            }
        });
