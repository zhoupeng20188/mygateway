/**
 * Title: JWTBadException.java
 * Description: 
 * Company: 长江数字
 * @author JIMO
 * @date 2019年7月12日
 * @version 1.0
 */
package com.example.mygateway.exception;

/**
 * Title: JWTBadException
 * Description: 
 * @author JIMO
 * @date 2019年7月12日
 */
public class JWTBadException extends RuntimeException {

    private static final long serialVersionUID = -1506290011065493801L;

    //自定义构造器，只保留一个，让其必须输入错误内容
    public JWTBadException(String msg) {
        super(msg);
    }
  
}
