package com.shgx.aop.aop;

import com.shgx.aop.annotation.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: guangxush
 * @create: 2019/07/27
 */

@Component
@Aspect
public class MyLogAction {
    private final String POINT_CUT = "execution(* com.shgx.aop..*(..))";

    @Pointcut(POINT_CUT)
    private void pointcut() {
    }

    @After("pointcut()")
    public void doAfterAdvice(JoinPoint pjp) throws Throwable {
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();

        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Method method = target.getClass().getMethod(methodName, parameterTypes);
        if (null != method) {
            // 获取方法（此为自定义注解）
            MyLog op = method.getAnnotation(MyLog.class);
            if (op != null) {
                // 获取注解的modules 设为操作模块
                System.out.println(op.module());
                // 获取注解的methods 设为执行方法
                System.out.println(op.method());
                //NOTE!!--这里可操作：将得到的module、method用logService（自己定义）的方法存到数据库
            }
        }
    }
}

