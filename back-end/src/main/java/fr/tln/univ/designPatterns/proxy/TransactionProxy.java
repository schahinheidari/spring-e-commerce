package fr.tln.univ.designPatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionProxy implements InvocationHandler {
    private DepartmentDao departmentDao;

    public TransactionProxy(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public static DepartmentDao createTransactionProxy(DepartmentDao dao){
        return (DepartmentDao) Proxy.newProxyInstance(dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),
                new TransactionProxy(dao));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(departmentDao, args);
        //departmentDao.getDbConnection().getConnection().commit();
        return invoke;
    }
}
