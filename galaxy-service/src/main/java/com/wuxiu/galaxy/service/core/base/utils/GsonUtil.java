package com.wuxiu.galaxy.service.core.base.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.NoArgsConstructor;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author: wuxiu
 * @date: 2019/4/14 21:05
 */
@NoArgsConstructor
public class GsonUtil {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T toObject(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成bean
     * @param gsonString
     * @param typeOfT
     * @param <T>
     * @return
     */
    public static <T> T toObject(String gsonString, Type typeOfT) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, typeOfT);
        }
        return t;
    }

    /**
     * 转成list
     * 解决泛型问题
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }


    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> toListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 用于拷贝对象
     * @param source
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> T convert(S source, Class<T> target) {
        assert source != null;
        return GsonUtil.toObject(toJsonString(source), target);
    }


    /**
     * Gson的fromJson
     * @param source
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String source, Type type) {
        return  GsonUtil.getGson().fromJson(source, type);
    }
    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> toMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    static class LocalDateAdapter implements JsonSerializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            // "yyyy-mm-dd"
            return new JsonPrimitive(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
    }

    static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime> {

        @Override
        public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
            // "yyyy-mm-dd"
            return new JsonPrimitive(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }

    public static class EnumAdapterFactory implements TypeAdapterFactory
    {

        @Override
        public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type)
        {
            Class<? super T> rawType = type.getRawType();
            if (rawType.isEnum())
            {
                return new EnumTypeAdapter<T>();
            }
            return null;
        }

        public class EnumTypeAdapter<T> extends TypeAdapter<T>
        {
            @Override
            public void write(JsonWriter out, T value) throws IOException
            {
                if (value == null || !value.getClass().isEnum())
                {
                    out.nullValue();
                    return;
                }

                try
                {
                    out.beginObject();
                    out.name("value");
                    out.value(value.toString());
                    Arrays.stream(Introspector.getBeanInfo(value.getClass()).getPropertyDescriptors())
                            .filter(pd -> pd.getReadMethod() != null && !"class".equals(pd.getName()) && !"declaringClass".equals(pd.getName()))
                            .forEach(pd -> {
                                try
                                {
                                    out.name(pd.getName());
                                    out.value(String.valueOf(pd.getReadMethod().invoke(value)));
                                } catch (IllegalAccessException | InvocationTargetException | IOException e)
                                {
                                    e.printStackTrace();
                                }
                            });
                    out.endObject();
                } catch (IntrospectionException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public T read(JsonReader in) throws IOException
            {
                // Properly deserialize the input (if you use deserialization)
                return null;
            }
        }
    }

    public static Gson getGson() {
        Gson gson = new GsonBuilder()
                //序列化null
                .serializeNulls()
                // 设置日期时间格式，另有2个重载方法
                // 在序列化和反序化时均生效
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 序列化localdatetime
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapterFactory(new EnumAdapterFactory())
                // 禁此序列化内部类
                .disableInnerClassSerialization()
                //禁止转义html标签
                .disableHtmlEscaping()
                .create();
        return gson;
    }
}
