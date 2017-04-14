package com.yangchengzhuo.android.graduate;

import android.content.Context;

import com.yangchengzhuo.android.Interface.StudentInterface;
import com.yangchengzhuo.android.Interface.TeacherInterface;
import com.yangchengzhuo.android.Operate.StudentOperate;
import com.yangchengzhuo.android.Operate.TeacherOperate;

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
    public static StudentInterface getStudentInterface(Context context) {
        return new StudentOperate(context);
    }

    /**
     * 返回TeacherInterface 对象
     *
     * @param context
     *            上下文对象
     * @return TeacherInterface对象
     */
    public static TeacherInterface getTeacherInterface(Context context) {
        return new TeacherOperate(context);
    }


}
