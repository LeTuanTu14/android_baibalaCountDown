package com.example.baibalacountdown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int manghinhbai[]={
            R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
            R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,
            R.drawable.cj,R.drawable.cq,R.drawable.ck,
            R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,
            R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,
            R.drawable.dj,R.drawable.dq,R.drawable.dk,
            R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,
            R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
            R.drawable.hj,R.drawable.hq,R.drawable.hk,
            R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,
            R.drawable.s6,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,
            R.drawable.sj,R.drawable.sq,R.drawable.sk};
    int sltcuaA;
    int sltcuaB;
    CountDownTimer Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ed_time= findViewById(R.id.edt_time);
        ImageView la1= findViewById(R.id.la1);
        ImageView la2= findViewById(R.id.la2);
        ImageView la3= findViewById(R.id.la3);
        ImageView la4= findViewById(R.id.la4);
        ImageView la5= findViewById(R.id.la5);
        ImageView la6= findViewById(R.id.la6);
        TextView tv_kqban= findViewById(R.id.tv_kqban);
        TextView tv_kqmay= findViewById(R.id.tv_kqmay);
        TextView tv_kqchungcuoc= findViewById(R.id.tv_kqchungcuoc);
        Button rutBai= findViewById(R.id.bt_rutbai);
        Button dung= findViewById(R.id.bt_stop);


        rutBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sltcuaA=0;
                sltcuaB=0;
                int time= Integer.parseInt(ed_time.getText().toString());
                if(time > 0){
                    Timer = new CountDownTimer(time*1000,2000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                            int[] saulabai= lay6SoNgauNhien(0,51);
                            int[] baiban=  new int[3];
                            int[] baimay= new int[3];
                            baiban[0]=saulabai[0];
                            baiban[1]=saulabai[1];
                            baiban[2]=saulabai[2];
                            baimay[0]=saulabai[3];
                            baimay[1]=saulabai[4];
                            baimay[2]=saulabai[5];
                            la1.setImageResource(manghinhbai[baiban[0]]);
                            la2.setImageResource(manghinhbai[baiban[1]]);
                            la3.setImageResource(manghinhbai[baiban[2]]);
                            la4.setImageResource(manghinhbai[baimay[0]]);
                            la5.setImageResource(manghinhbai[baimay[1]]);
                            la6.setImageResource(manghinhbai[baimay[2]]);
                            int a=tinhKQ(baiban);
                            int b=tinhKQ(baimay);
                            tv_kqban.setText(thongbao(a));
                            tv_kqmay.setText(thongbao(b));
                            tv_kqchungcuoc.setText(thongbaoKQTungVong(a,b));
                            demLanThang(a,b);
                        }

                        @Override
                        public void onFinish() {
                            if(sltcuaA>sltcuaB)
                                tv_kqchungcuoc.setText(thongbaoKQChungCuoc());
                        }
                    }.start();

                }else{
                    Toast.makeText(MainActivity.this,"Thời gian không hợp lệ!!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        dung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timer.cancel();
                tv_kqchungcuoc.setText(thongbaoKQChungCuoc());
            }
        });
    }

    //-----------------------------------------------------

    private String thongbaoKQChungCuoc(){
        if(sltcuaA>sltcuaB)
            return "Bạn chiến thắng!! Bạn thắng "+sltcuaA+" lần, Máy thắng "+sltcuaB+" lần";
        else if(sltcuaB>sltcuaA)
            return "Bạn thua!! Máy thắng "+sltcuaB+" lần, Bạn thắng "+sltcuaA+" lần";
        else
            return "Hòa!! Bạn thắng "+sltcuaA+" lần, Máy thắng "+sltcuaB+" lần";
    }

    //-----------------------------------------------------

    private void demLanThang(int a, int b){
        if(a>b)
            sltcuaA++;
        else if(b>a)
            sltcuaB++;
    }

    //-----------------------------------------------------

    private String thongbaoKQTungVong(int a, int b){
        if(a>b)
            return "Bạn chiến thắng";
        else if(b>a)
            return "Bạn thua";
        else
            return "Hòa";
    }

    //-----------------------------------------------------

    private String thongbao(int a){
        if(a==0)
            return "Kết quả: bù";
        else if(a==10)
            return "Kết quả: 3 con tây ";
        return "Kết quả: "+a+" nút";
    }

    //-----------------------------------------------------

    private int tinhKQ(int[] arr){
        int kq=0;
        if(tinhSoTay(arr) ==3)
            kq=10;
        else{
            int tong=0;
            for (int i=0;i<arr.length;i++)
                if(arr[i]%13 < 10)
                    tong+= arr[i] %13+1;

            if(tong%10 ==0)
                kq=0;
            else
                kq=(tong%10);
        }
        return kq;
    }

    //-----------------------------------------------------

    private int tinhSoTay(int[] arr){
        int k=0;
        for (int i=0; i<arr.length;i++){
            if(arr[i]%13>=10)
                k++;

        }
        return k;
    }

    //-----------------------------------------------------

    private int[] lay6SoNgauNhien(int min, int max){
        int[] baso= new int[6];
        int i=0;
        baso[i++]= random(min,max);
        do {
            int k= random(min,max);
            if(!ktraTrung(k,baso))
                baso[i++]=k;
        }while (i<6);
        return baso;
    }

    //-----------------------------------------------------

    private boolean ktraTrung(int k, int[] arr){
        for (int i=0;i<arr.length;i++){
            if(arr[i] == k)
                return true;

        }
        return false;
    }

    //-----------------------------------------------------

    private int random(int min, int max){
        return min+ (int)(Math.random()*((max-min)+1));
    }

}