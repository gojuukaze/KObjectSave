## KObjectSave
`KObjectSave`用来代替android SharedPreferences的键值存储方式，
支持多种类型（int, string ,Date ,bool ,List），  
它可以方便的存储应用的设置选项，用户的点击记录等


## 添加依赖
在app的build.gradle中添加
```
dependencies {
    compile 'cn.ikaze.KObjectSave:library:1.0.0'
}
```

## 使用
首先要有一个基础的类用于存储
```java
public class Student {
    int no = 1; //default 1
    String name = "gojuukaze";//default gojuukaze
    Date birthday = new Date(); //default now
    List<String> subjects;
    
    public String toString() {
        Format format = new SimpleDateFormat("yyyy-MM-dd");
        String birthdayString = format.format(birthday);
        return "No." + no + ", name: " + name + ", birthday: " + birthdayString + ", subjects: " + subjects;
    }
}
```
使用 `KObjectSave` 存储 Student
```java

KObjectSave kSave = new KObjectSave(this, Student.class);
Student st = (Student) kSave.getObj();

st.no=23;
st.name="A";
st.subjects=new ArrayList<String>(){{add("math");add("physics");}};

try {
  kSave.save(st);
} catch (IOException | IllegalAccessException e) {
  e.printStackTrace();
}
```

加载
```java
Student tempSt = (Student) new KObjectSave(this, Student.class).getObj();
lod.d("KObjectSave",""+tempSt);
```

## 例子
[Example](https://github.com/gojuukaze/KObjectSave/tree/master/sample)


## bintray地址
[Here](https://bintray.com/gojuukaze/maven/KObjectSave)

## 注意！！

数据没有加密，不要用户存储用户密码等隐私数据

## License

    Copyright 2014-2017 Alexey Danilov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
