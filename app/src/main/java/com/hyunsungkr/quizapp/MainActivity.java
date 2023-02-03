package com.hyunsungkr.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hyunsungkr.quizapp.model.Quiz;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 화면의 뷰 연결용 멤버 변수
    TextView txtQuiz;
    ProgressBar progressBar;
    TextView txtResult;
    Button btnTrue;
    Button btnFalse;

    // 현재 퀴즈의 인덱스 값!
    int currentQuizIndex = 0;

    // 데이터 저장용 멤버 변수
    ArrayList<Quiz> quizArrayList = new ArrayList<>();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 화면의 뷰 연결
        txtQuiz = findViewById(R.id.txtQuiz);
        txtResult = findViewById(R.id.txtResult);
        progressBar = findViewById(R.id.progressBar);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);

        // 2. 퀴즈를 만든다! => 퀴즈 클래스를 메모리에 만들어준다.
        //    => 클래스를 가지고 객체생성 해준다.
        //      퀴즈가 10개이니 객체를 10개 만들어준다.

        setQuiz();

        // 3. 문제를 출제한다. ( 화면에 )
        Quiz q = quizArrayList.get(currentQuizIndex);

        txtQuiz.setText(q.question);

        // 4. ProgressBar를 표시하자.
        progressBar.setProgress(currentQuizIndex+1);


        // 5. 참 / 거짓 버튼에 대한 처리
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 현재 문제의 정답을 가져온다.
                Quiz q = quizArrayList.get(currentQuizIndex);

                // 2. 이 버튼이 트루버튼이므로
                // 정답이 트루이면
                // 결과에 "정답입니다"라고 표시
                // 그렇지 않으면 "틀렸습니다."
                if(q.answer==true){
                    txtResult.setText("정답입니다.");
                    count = count +1;
                }else{
                    txtResult.setText("틀렸습니다.");
                }
                // 3. 다음 문제를 출제한다.
                if(currentQuizIndex==9){

                    showAlertDialog();

                    return;
                }else {


                    currentQuizIndex = currentQuizIndex + 1;

                    q = quizArrayList.get(currentQuizIndex);
                    txtQuiz.setText(q.question);

                    // 4. 프로그레스바도 하나 증가
                    progressBar.setProgress(currentQuizIndex + 1);
                }
            }
        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 현재 문제의 정답을 가져온다.
                Quiz q = quizArrayList.get(currentQuizIndex);

                // 2. 이 버튼이 펄스버튼이므로
                // 정답이 폴스이면
                // 결과에 "정답입니다"라고 표시
                // 그렇지 않으면 "틀렸습니다."
                if(q.answer==false){
                    txtResult.setText("정답입니다.");
                    count = count +1;
                }else{
                    txtResult.setText("틀렸습니다.");
                }
                // 3. 다음 문제를 출제한다.
                if(currentQuizIndex==quizArrayList.size()-1){
                    showAlertDialog();
                    return;


                }else {


                    currentQuizIndex = currentQuizIndex + 1;

                    q = quizArrayList.get(currentQuizIndex);
                    txtQuiz.setText(q.question);

                    // 4. 프로그레스바도 하나 증가
                    progressBar.setProgress(currentQuizIndex + 1);
                }
            }
        });


    }

    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // 이 다이얼로그의 외곽부분을 눌렀을 때, 사라지지 않도록 하는 코드
        builder.setCancelable(false);
        builder.setTitle("퀴즈 끝");
        builder.setMessage("맞춘 문제는 "+count+"개 입니다. 확인을 누르시면 퀴즈가 다시 시작됩니다.");
        builder.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 액티비티 종료!!!
                finish();
            }
        });
//                    builder.setNeutralButton("중립",null);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 확인 버튼을 눌렀을 때 실행할 코드 작성.

                // 1. 첫번째 문제가 다시 화면에 나와야한다.
                currentQuizIndex=0;
                Quiz q=quizArrayList.get(0);
                txtQuiz.setText(q.question);

                //2. 프로그레스바도 처음부터 나와야한다.
                progressBar.setProgress(currentQuizIndex+1);

                //3. 정답 갯수는 0으로 만들어야한다.
                count = 0;

            }
        });
        builder.show();

    }

    private void setQuiz() {
        Quiz q= new Quiz( R.string.q1,true );
        quizArrayList.add(q);

        q = new Quiz(R.string.q2,false);
        quizArrayList.add(q);

        q = new Quiz(R.string.q3,true);
        quizArrayList.add(q);

        q = new Quiz(R.string.q4,false);
        quizArrayList.add(q);

        q = new Quiz(R.string.q5,true);
        quizArrayList.add(q);

        q = new Quiz(R.string.q6,false);
        quizArrayList.add(q);

        q = new Quiz(R.string.q7,true);
        quizArrayList.add(q);

        q = new Quiz(R.string.q8,false);
        quizArrayList.add(q);

        q = new Quiz(R.string.q9,true);
        quizArrayList.add(q);

        q = new Quiz(R.string.q10,false);
        quizArrayList.add(q);

    }
}