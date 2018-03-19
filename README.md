## KObjectSave
`KObjectSave` is developed to replace android `SharedPreferences`, it supports int,string,Date,List and more  
代替android SharedPreferences的键值存储方式，支持多种类型  
[中文README](https://github.com/gojuukaze/KObjectSave/blob/master/README.zh.md)

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


## NOTICE !!

*KObjectSave dose not encrypt data, do not used to save password*

## License

     Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/
