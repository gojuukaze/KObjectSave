package cn.ikaze.kobjectsave;

import android.content.Context;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

/**
 * Created by gojuukaze on 2018/2/8.
 * Email: i@ikaze.uu.me
 */

public class KObjectSave {
    private Class objClass;
    private Object obj;
    private String fileName = "objClass";
    private File file;
    private Context context;


    public KObjectSave(Context context, Class objClass) {
        this.context = context;
        this.objClass = objClass;
        file = new File(fileName);
        loadObjFromFile();

    }

    public KObjectSave(Context context, Class objClass, String fileName) {
        this.context = context;
        this.objClass = objClass;
        this.fileName = fileName;
        file = new File(fileName);
        loadObjFromFile();
    }

    public void save(Object obj) throws IOException, IllegalAccessException, NullPointerException {
        this.obj = obj;
        save();
    }

    public void save() throws IOException, IllegalAccessException, NullPointerException {
        if (this.obj == null)
            throw new NullPointerException("obj can not be null");
        ObjectOutputStream writer = null;
        try {
            writer = new ObjectOutputStream(context.openFileOutput(file.getName(), Context.MODE_PRIVATE));
            for (Field f : objClass.getDeclaredFields()) {
                f.setAccessible(true);
//                if (f.getName().equals("$change") ||f.getName().equals("serialVersionUID") )
//                    continue;
                writer.writeObject(f.getName());
                writer.writeObject(f.get(this.obj));

            }

        } finally {

            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }


    public void loadObjFromFile() {
        try {
            this.obj = objClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            this.obj = null;
        }
        InputStream in = null;
        try {
            in = context.openFileInput(file.getName());
        } catch (FileNotFoundException e) {
            return;
        }
        if (this.obj == null)
            return;


        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(in);
            Object fieldName;

            while ((fieldName = reader.readObject()) != null) {
                Field f = objClass.getDeclaredField((String) fieldName);
                f.setAccessible(true);
                Object fieldValue = reader.readObject();
                f.set(this.obj, fieldValue);
            }

        } catch (EOFException ignored) {
            // end of the file,it is not a error
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    public boolean deleteFile() {

        return context.deleteFile(file.getName());
    }

    public boolean fileExits() {
        InputStream in = null;
        try {
            in = context.openFileInput(file.getName());
        } catch (FileNotFoundException e) {

            return false;
        }
        try {
            in.close();
        } catch (Exception ignored) {
        }
        return true;
    }


}
