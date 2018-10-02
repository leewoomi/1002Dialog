package com.example.a503_08.a1002dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button multiselect;
    Button customdialog, popupbtn;
    //선택 여부를 판단하기 위한 배열
    boolean[] select = {false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        multiselect = (Button) findViewById(R.id.multiselect);


        multiselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("좋아하는 멤버")
                        .setIcon(android.R.drawable.btn_plus)
                        .setMultiChoiceItems(R.array.player, select, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                //int 누른 번호 boolean 체크 여부

                                select[which] = isChecked;


                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //arrays.xml 파일에 만든 문자열 배열 가져오기
                                String[] player = getResources().getStringArray(R.array.player);

                                //선택자 여부가 저장되어 있는 배열을 순회하면서
                                //true 일 때 player의 데이터를 문자열에 추가
                                String result = "";
                                for (int i = 0; i < select.length; i++) {
                                    if (select[i]) {
                                        result = result + player[i] + "\t";

                                    }
                                }

                                //선택한 데이터 문자열을 토스트로 출력
                                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();

            }


        });
        customdialog = (Button) findViewById(R.id.cutomdialog);


        customdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //레이아웃 파일의 내용 불러오기
                //Anonymous 클래스에서 사용하기 위해서 final을 붙임
                //Anonymous 클래스에서 자신을 포함하는 메소드의 자역변소를
                //사용할 수 없음.
                //final은 가능
                final LinearLayout login = (LinearLayout) View.inflate(MainActivity.this, R.layout.login, null);

                new AlertDialog.Builder(MainActivity.this).setIcon(android.R.drawable.sym_def_app_icon)
                        .setTitle("로그인")
                        .setView(login)
                        .setNegativeButton("취소", null)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //로그인
                                EditText id = (EditText) login.findViewById(R.id.id);

                                //비밀번호
                                EditText pw = (EditText) login.findViewById(R.id.pw);

                                String result = "id : " + id.getText().toString() + ",pw : " + pw.getText().toString();

                                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();

                            }
                        }).show();

            }
        });


        //popup.xml 내용을 가지는 뷰를 생성
        final LinearLayout popup = (LinearLayout) View.inflate(MainActivity.this, R.layout.popup, null);
        popupbtn = (Button) findViewById(R.id.pupupwindow);

        popupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //팝업 윈도우
                final PopupWindow popupWindow = new PopupWindow(popup, 500, 300,true);
                //화면출력 -첫번째 매개변수는 기준 뷰
                //두번째와 세번째는 좌표 네번째 포커스 여부
                popupWindow.showAtLocation(popupbtn, Gravity.NO_GRAVITY,300,900);

                Button close = (Button)popup.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });




    }
}
