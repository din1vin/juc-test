# juc-test
这是一个学习高并发的项目
## 基础部分

### 线程创建
> 1. 继承Thread
> 2. 实现Runnable
> 3. 实现Callable

使用继承方式的好处就是方便传参,可以在线程对象中添加成员变量,通过set方法方便传参;
使用Runnable方式只能使用主线程中声明为final的变量;
前面两种方式都没办法拿到任务返回的结果,Callable可以

### 线程等待
Object 类中有一个基本方法 wait(),调用该方法可以使线程进入阻塞态,把共享变量锁资源让出来.

### 线程唤醒
Object 类中notify()方法可以唤醒一个正在等待对象监视器的单个线程,选择是任意的. notifyAll()则可以唤醒所有等待线程.

## Lock锁
> **synchronized 和 Lock锁的区别**
> 1. synchronized 内置的Java关键字; Lock 一个Java类
> 2. synchronized 无法判断获取锁的状态; Lock 可以判断是否获取到了锁
> 3. synchronized 会自动释放锁; Lock 必须手动释放(如果不释放,死锁)
> 4. synchronized 如果某个线程阻塞,其他线程会一直等待; Lock 锁不会一直等待;
> 5. synchronized 不可以中断,非公平; Lock 可以判断锁, 可以设置是否公平
> 6. synchronized 适合锁少量代码同步问题; Lock锁适合大量代码同步问题