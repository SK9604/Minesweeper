package 지뢰찾기;
import java.util.Scanner;
import java.util.Random;

public class GameSet{

	Scanner sc = new Scanner(System.in);
	Random r = new Random();

	int col = 0, row = 0; //세로 가로 0초기화 , 메소드에서 쓸 변수들에는 값을 대입할 것이므로 여기서 선언.
	int numofmine = 0; //마인개수 초기화
	int count = 0; // 지뢰를 numofmine만큼 심을 때 필요한 변수
	String [][] inboard; String [][] outboard; // 속판과 겉판 선언

	void SetSize() {
		System.out.println("지뢰찾기판 가로의 길이를 설정해주세요: ");
		row = sc.nextInt();
		System.out.println("지뢰찾기판 세로의 길이를 설정해주세요: ");
		col = sc.nextInt();
	}

	void SetMine() {
		System.out.println("지뢰의 개수를 설정해주세요: ");
		numofmine = sc.nextInt();
	}

	String SetNumOfAroundMine(String s) { //String 타입을 Intger 타입으로 바꾸고 다시 String타입으로 바꾸는 메소드
		if(s != "*"){                     //또한 지뢰주변에 숫자를 대입할 때 대일할 자리가 지뢰인 경우는 오류가 발생하지 않도록 만듬.
			int i = Integer.parseInt(s) + 1;
			s = Integer.toString(i);
			return s;
		}else return s;
	}

	void CreatMine() {
		for(int i =0; i<numofmine;i++){ //설정한 지뢰의 갯수만큼 랜덤한 좌표에 지뢰를 생성한다.
			int ro = r.nextInt(row);
			int co = r.nextInt(col);
			if(inboard[ro][co]=="*"){ //지뢰를 만들 좌표에 이미 지뢰가 있을 경우 
				i--;
				continue;
			}
			else{
				inboard[ro][co]="*";
				if(ro>0&&co>0&&ro<row-1&&co<col-1){
					inboard[ro-1][co-1] = SetNumOfAroundMine(inboard[ro-1][co-1]);
					inboard[ro][co-1] = SetNumOfAroundMine(inboard[ro][co-1]);
					inboard[ro+1][co-1] = SetNumOfAroundMine(inboard[ro+1][co-1]);
					inboard[ro+1][co] = SetNumOfAroundMine(inboard[ro+1][co]);
					inboard[ro+1][co+1]= SetNumOfAroundMine(inboard[ro+1][co+1]);
					inboard[ro][co+1]= SetNumOfAroundMine(inboard[ro][co+1]);
					inboard[ro-1][co+1]= SetNumOfAroundMine(inboard[ro-1][co+1]);
					inboard[ro-1][co]= SetNumOfAroundMine(inboard[ro-1][co]);
				}
				else if(ro>0&&ro<row-1&&co==0){
					inboard[ro-1][co]= SetNumOfAroundMine(inboard[ro-1][co]);
					inboard[ro-1][co+1]= SetNumOfAroundMine(inboard[ro-1][co+1]);
					inboard[ro][co+1]= SetNumOfAroundMine(inboard[ro][co+1]);
					inboard[ro+1][co+1]= SetNumOfAroundMine(inboard[ro+1][co+1]);
					inboard[ro+1][co]= SetNumOfAroundMine(inboard[ro+1][co]);
				}
				else if(ro==row-1&&co==0){
					inboard[ro-1][co]= SetNumOfAroundMine(inboard[ro-1][co]);
					inboard[ro-1][co+1]= SetNumOfAroundMine(inboard[ro-1][co+1]);
					inboard[ro][co+1]= SetNumOfAroundMine(inboard[ro][co+1]);
				}
				else if(ro==0&&co==0){
					inboard[ro][co+1]= SetNumOfAroundMine(inboard[ro][co+1]);
					inboard[ro+1][co+1]= SetNumOfAroundMine(inboard[ro+1][co+1]);
					inboard[ro+1][co]= SetNumOfAroundMine(inboard[ro+1][co]);
				}
				else if(ro==0&&co==col-1){
					inboard[ro][co-1]= SetNumOfAroundMine(inboard[ro][co-1]);
					inboard[ro+1][co-1]= SetNumOfAroundMine(inboard[ro+1][co-1]);
					inboard[ro+1][co]= SetNumOfAroundMine(inboard[ro+1][co]);
				}
				else if(ro==0&&co<col-1&&co>0){
					inboard[ro][co-1] = SetNumOfAroundMine(inboard[ro][co-1]);
					inboard[ro][co+1]=SetNumOfAroundMine(inboard[ro][co+1]);
					inboard[ro+1][co-1] = SetNumOfAroundMine(inboard[ro+1][co-1]);
					inboard[ro+1][co] = SetNumOfAroundMine(inboard[ro+1][co]);
					inboard[ro+1][co+1] = SetNumOfAroundMine(inboard[ro+1][co+1]);
				}
				else if(ro==row-1&&co>0&&co<col-1){
					inboard[ro][co+1]= SetNumOfAroundMine(inboard[ro][co+1]);
					inboard[ro][co-1]= SetNumOfAroundMine(inboard[ro][co-1]);
					inboard[ro-1][co+1]= SetNumOfAroundMine(inboard[ro-1][co+1]);
					inboard[ro-1][co]= SetNumOfAroundMine(inboard[ro-1][co]);
					inboard[ro-1][co+1]= SetNumOfAroundMine(inboard[ro-1][co+1]);

				}
				else if(ro>0&&ro<row-1&&co==col-1){
					inboard[ro-1][co-1] = SetNumOfAroundMine(inboard[ro-1][co-1]);
					inboard[ro][co-1] = SetNumOfAroundMine(inboard[ro][co-1]);
					inboard[ro+1][co-1] = SetNumOfAroundMine(inboard[ro+1][co-1]);
					inboard[ro-1][co]= SetNumOfAroundMine(inboard[ro-1][co]);
					inboard[ro+1][co]= SetNumOfAroundMine(inboard[ro+1][co]);
				}
				else if(ro==row-1&&co==col-1){
					inboard[ro][co-1]= SetNumOfAroundMine(inboard[ro][co-1]);
					inboard[ro-1][co-1]= SetNumOfAroundMine(inboard[ro-1][co-1]);
					inboard[ro-1][co]= SetNumOfAroundMine(inboard[ro-1][co]);
				}
			}
		}
	}

	void PrintBoard() {
		System.out.println();
		System.out.print("    "+"1 ");
		for(int i=2; i <= row; i++) {
			System.out.print(i+" ");
		}

		System.out.println();
		System.out.println();

		for(int j=1; j<= col ; j++) {
			System.out.print(j+"   ");
			for(int l=0; l<row ;l++)
				System.out.print(outboard[l][j-1]+" "); 

			System.out.println();

		}
	}

	void Creatboard() {
		SetSize(); SetMine();
		inboard = new String[row][col];
		outboard = new String[row][col];

		for(int i=0;i<row;i++) {           //inboard와 outboard를 문자로 초기화
			for(int j=0;j<col;j++) {
				outboard[i][j] = "■";
				inboard[i][j] = "0";
			}
		}
		CreatMine();
		PrintBoard();
	}
}