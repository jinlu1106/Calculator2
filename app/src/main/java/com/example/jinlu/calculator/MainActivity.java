package com.example.jinlu.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //数字按钮声明
    Button btn_zero;
    Button btn_one;
    Button btn_two;
    Button btn_three;
    Button btn_four;
    Button btn_five;
    Button btn_six;
    Button btn_seven;
    Button btn_eight;
    Button btn_nine;

    Button btn_point;
    Button btn_plus;
    Button btn_mul;
    Button btn_subtract;
    Button btn_divide;
    Button btn_equal;
    Button btn_clear;
    Button btn_sign;
    Button btn_delete;

    EditText cal_input;
    boolean clear_flag ;//清空标识，布尔值，默认为假

    //重写onCreate方法：1.完成活动的初始化操作；2.加载布局；3.设置监听事件；
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化按钮
        btn_zero = (Button)findViewById(R.id.btn_zero);
        btn_one = (Button)findViewById(R.id.btn_one);
        btn_two = (Button)findViewById(R.id.btn_two);
        btn_three = (Button)findViewById(R.id.btn_three);
        btn_four = (Button)findViewById(R.id.btn_four);
        btn_five = (Button)findViewById(R.id.btn_five);
        btn_six = (Button)findViewById(R.id.btn_six);
        btn_seven = (Button)findViewById(R.id.btn_seven);
        btn_eight = (Button)findViewById(R.id.btn_eight);
        btn_nine = (Button)findViewById(R.id.btn_nine);
        btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_divide = (Button)findViewById(R.id.btn_divide);
        btn_equal = (Button)findViewById(R.id.btn_equal);
        btn_mul = (Button)findViewById(R.id.btn_mul);
        btn_plus = (Button)findViewById(R.id.btn_plus);
        btn_point = (Button)findViewById(R.id.btn_point);
        btn_sign = (Button)findViewById(R.id.btn_sign);
        btn_subtract = (Button)findViewById(R.id.btn_subtract);

        //实例化显示屏
        cal_input = (EditText)findViewById(R.id.cal_input);

        //设置按钮点击事件  监听者
        btn_zero.setOnClickListener(this);                  //方法回调
        btn_one.setOnClickListener(this);                   //参数this就是Activity本身
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_seven.setOnClickListener(this);
        btn_eight.setOnClickListener(this);
        btn_nine.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_sign.setOnClickListener(this);
        btn_subtract.setOnClickListener(this);
    }

    //设置onClick方法
    public void onClick(View v) {
        String str = cal_input.getText().toString();
        switch (v.getId()) {                    //使用switch控制不同的Button Click事件响应方法
            case R.id.btn_zero:
            case R.id.btn_one:
            case R.id.btn_two:
            case R.id.btn_three:
            case R.id.btn_four:
            case R.id.btn_five:
            case R.id.btn_six:
            case R.id.btn_seven:
            case R.id.btn_eight:
            case R.id.btn_nine:
            case R.id.btn_point:
            case R.id.btn_sign:
                if(clear_flag){
                    clear_flag = false;
                    str = "";
                    cal_input.setText("");
                }
                cal_input.setText(str+""+((Button)v).getText());
                break;


            case R.id.btn_plus:
            case R.id.btn_subtract:
            case R.id.btn_mul:
            case R.id.btn_divide:

                if(clear_flag){
                    clear_flag = false;
                    str = "";
                    cal_input.setText("");
                }
                cal_input.setText(str+" "+((Button)v).getText()+" ");
                break;


            case R.id.btn_delete:
                if(clear_flag){
                    clear_flag = false;
                    str = "";
                    cal_input.setText("");
                }else if(str!=null&&!str.equals(" ")){
                    cal_input.setText(str.substring(0,str.length()-1));
                }break;


            case R.id.btn_clear:
                clear_flag =false ;
                str ="" ;
                cal_input.setText("");


            case R.id.btn_equal:
                getResult();
                break ;
        }
    }

    //等号   单独的调用运算结果
    private void getResult(){
        String deo = cal_input.getText().toString();
        if(deo == null||deo.equals(" ")){
            return;
        }
        if(!deo.contains(" ")){
            return;
        }
        if(clear_flag){
            clear_flag = false;
            return;
        }
        clear_flag = true;      //修改clear_flag的布尔值为真
        double result = 0;
        String s1 = deo.substring(0,deo.indexOf(" "));          //运算符前面的字符
                        // indexOf函数，找到空格之前的数字，返回运算符前面数字的位置
        String op = deo.substring(deo.indexOf(" ")+1,deo.indexOf(" ")+2) ;
                        //找到运算符的位置
        String s2 = deo.substring(deo.indexOf(" ")+3) ;
                        //找到运算符后面的数字
        if (!s1.equals(" ")&&!s2.equals(" ")){
            double d1 = Double.parseDouble(s1) ;        //Double.parseDouble方法返回一个新的
            double d2 = Double.parseDouble(s2) ;        //双初始化为指定字符串表示的值，转为double型
            if (op.equals("+")){
                result = d1 + d2 ;

            }else  if (op.equals("-")){
                result = d1 - d2 ;

            }else  if (op.equals("*")){
                result = d1 * d2 ;

            }else  if (op.equals("/")){
                if(d2 == 0){
                    result = 0 ;
                }else {
                    result = d1/d2 ;
                }
            }

            //****************************************
            if (s1.contains(".")&&s2.contains(".")) {

                cal_input.setText(result+"");       //把result强转为字符型
            }else {
                cal_input.setText(result+"");
            }
            //****************************************
        }
    }
}
