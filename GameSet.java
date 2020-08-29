package ����ã��;
import java.util.Scanner;
import java.util.Random;

public class GameSet{

	Scanner sc = new Scanner(System.in);
	Random r = new Random();

	int col = 0, row = 0; //���� ���� 0�ʱ�ȭ , �޼ҵ忡�� �� �����鿡�� ���� ������ ���̹Ƿ� ���⼭ ����.
	int numofmine = 0; //���ΰ��� �ʱ�ȭ
	int count = 0; // ���ڸ� numofmine��ŭ ���� �� �ʿ��� ����
	String [][] inboard; String [][] outboard; // ���ǰ� ���� ����

	void SetSize() {
		System.out.println("����ã���� ������ ���̸� �������ּ���: ");
		row = sc.nextInt();
		System.out.println("����ã���� ������ ���̸� �������ּ���: ");
		col = sc.nextInt();
	}

	void SetMine() {
		System.out.println("������ ������ �������ּ���: ");
		numofmine = sc.nextInt();
	}

	String SetNumOfAroundMine(String s) { //String Ÿ���� Intger Ÿ������ �ٲٰ� �ٽ� StringŸ������ �ٲٴ� �޼ҵ�
		if(s != "*"){                     //���� �����ֺ��� ���ڸ� ������ �� ������ �ڸ��� ������ ���� ������ �߻����� �ʵ��� ����.
			int i = Integer.parseInt(s) + 1;
			s = Integer.toString(i);
			return s;
		}else return s;
	}

	void CreatMine() {
		for(int i =0; i<numofmine;i++){ //������ ������ ������ŭ ������ ��ǥ�� ���ڸ� �����Ѵ�.
			int ro = r.nextInt(row);
			int co = r.nextInt(col);
			if(inboard[ro][co]=="*"){ //���ڸ� ���� ��ǥ�� �̹� ���ڰ� ���� ��� 
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

		for(int i=0;i<row;i++) {           //inboard�� outboard�� ���ڷ� �ʱ�ȭ
			for(int j=0;j<col;j++) {
				outboard[i][j] = "��";
				inboard[i][j] = "0";
			}
		}
		CreatMine();
		PrintBoard();
	}
}