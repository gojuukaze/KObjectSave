## KObjectSave
`KObjectSave` is developed to replace android `SharedPreferences`, it supports int,string,Date,List and more  
代替android SharedPreferences的键值存储方式，支持多种类型


## Get started
Just add dependency (`KObjectSave` is available in jcenter):
```
dependencies {
    compile 'cn.ikaze.KObjectSave:library:1.0.0'
}
```

## Usage

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
use `KObjectSave` to save Student
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

load data
```java
Student tempSt = (Student) new KObjectSave(this, Student.class).getObj();
lod.d("KObjectSave",""+tempSt);
```

## Example
[Example](https://github.com/gojuukaze/KObjectSave/tree/master/sample)


## Where published?
[Here](https://bintray.com/gojuukaze/maven/KObjectSave)


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
