package com.yangchengzhuo.android.graduate;

import android.content.Context;

import com.yangchengzhuo.android.Interface.StudentInterface;
import com.yangchengzhuo.android.Interface.TeacherInterface;

/**
 * Created by oliver_yang .
 */
public class Graduate {
    /**
     * 返回StudentInterface对象
     *
     * @param context
     *            　上下文对象
     * @return　StudentInteface对象
     */
    public static StudentInterface getStudentDao(Context context) {
        return new StudentDaoBean(context);
    }

    /**
     * 返回TeacherInterface 对象
     *
     * @param context
     *            上下文对象
     * @return TeacherInterface对象
     */
    public static TeacherInterface getTeacherDao(Context context) {
        return new TeacherDaoBean(context);
    }


}
