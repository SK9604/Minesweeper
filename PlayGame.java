package ����ã��;
public class PlayGame extends GameSet{

	int flag = 0; //����� �Ǵ��ϴ� ����
	int x = 0, y = 0;
	int re; //�Ǻ��Ҷ� while������ �������� ���δ�.
	void OnClick() { 
		//����: Ŭ���� ���������� ���ڱ��� ������
		//����: Ȯ�εǴ� �迭�κ� ����[i][j] = ����[i][j]
		//���ϴ� �·Ḧ Ŭ�� �ϸ� ��ǻ�ͻ󿡼��� -1�� ������� �Ѵ�.

		System.out.println("x��ǥ ���� (1~"+row+")�߿� ������ x��ǥ�� �Է��ϼ���");
		x = sc.nextInt();
		System.out.println("y��ǥ ���� (1~"+col+")�߿� ������ y��ǥ�� �Է��ϼ���");
		y = sc.nextInt();
		Distinguish(x, y);
	}

	void Distinguish(int x, int y) { //�Ǻ��ϴ� �޼ҵ� OnClick���� ��ǥ���� �޾Ƽ� ����.
		//Ŭ����ǥ�� ������ǥ�� �Ű������� �ް�
		//Ŭ����ǥ == ������ǥ���� Ȯ��
		//���ϰ��: flage= 1; ������ ���: �ٲ��� ���� ; NumOfMine = ���� �� �����϶� : flag = 2;
		//GetMassage()�� ����� ����� ���;

		int numofblacksquare = 0;
		for(int i=0; i<row ; i++){
			for(int j=0;j<col;j++) {
				if(outboard[i][j] == "��")
					numofblacksquare++;
			}
		}

		if(numofmine == numofblacksquare)  {
			flag = 2;
			PutOutMassage();
		}else if(inboard[x-1][y-1] == "*") { // ���� ã�� ���ڰ� �����ִ� ���
			flag = 1;
			outboard[x-1][y-1] = "*";
			PutOutMassage(); 
		}else if(Integer.parseInt(inboard[x-1][y-1]) >= 1) {
			outboard[x-1][y-1] = inboard[x-1][y-1];
			//������ǥ�䱸! �ƹ��͵� ���� ������ �ڿ������� OnClick���� �� �� �ִ�?           //
		}else {                     //�����ϴ�

			outboard[x-1][y-1] = "0";
			re = 0;
			while(re < row*col) {
				for(int i=0; i<row ; i++) {
					for(int j=0; j<col ; j++) {
						if(outboard[i][j] == "0") { 
							if((j-1) >= 0 && (j-1)<=(col-1) && inboard[i][j-1] =="0"){
								outboard[i][j-1] = "0";
							}else if((j-1) >= 0 && (j-1)<=(col-1) && Integer.parseInt(inboard[i][j-1])>=0){
								outboard[i][j-1] = inboard[i][j-1];
							}
							if((i-1) >= 0 && (i-1)<=(row-1) && inboard[i-1][j] =="0"){
								outboard[i-1][j] = "0";
							}else if((i-1) >= 0 && (i-1)<=(row-1) && Integer.parseInt(inboard[i-1][j])>=0){
								outboard[i-1][j] = inboard[i-1][j];
							}
							if((j+1) >= 0 && (j+1)<=(col-1) && inboard[i][j+1] =="0"){
								outboard[i][j+1] = "0";
							}else if((j+1) >= 0 && (j+1)<=(col-1) && Integer.parseInt(inboard[i][j+1])>=0){
								outboard[i][j+1] = inboard[i][j+1];
							}
							if((i+1) >= 0 && (i+1)<=(row-1) && inboard[i+1][j] =="0"){
								outboard[i+1][j] = "0";
							}else if((i+1) >= 0 && (i+1)<=(row-1) && Integer.parseInt(inboard[i+1][j])>=0){
								outboard[i+1][j] = inboard[i+1][j];
							}
						}
						// �� �κ��� ������ 1�̻��� ���ڵ��� ���������� �������� �ʴ´�.
						//ó������ ������ �� �� ����. �ֳ��ϸ� ���� � �簢���� �ֱ� ������ ���������� �񱳸� �ؾ��Ѵ�.
						if((outboard[i][j] == "1")||(outboard[i][j] == "2")||(outboard[i][j] == "3")||(outboard[i][j] == "4")||
								(outboard[i][j] == "5")||(outboard[i][j] == "6")||(outboard[i][j] == "7")||(outboard[i][j] == "8")){

							if((j-1) >= 0 && (j-1)<=(col-1) && Integer.parseInt(inboard[i][j-1])>=1){
								outboard[i][j-1] = inboard[i][j-1];
							}

							if((i-1) >= 0 && (i-1)<=(row-1) && Integer.parseInt(inboard[i-1][j])>=1){
								outboard[i-1][j] = inboard[i-1][j];
							}
							if((j+1) >= 0 && (j+1)<=(col-1) && Integer.parseInt(inboard[i][j+1])>=1){
								outboard[i][j+1] = inboard[i][j+1];
							}
							if((i+1) >= 0 && (i+1)<=(row-1) && Integer.parseInt(inboard[i+1][j])>=1){
								outboard[i+1][j] = inboard[i+1][j];
							}
						}

					}
				}
				re++;
			}
			for(int i=0; i< row ; i++) {
				for(int j=0; j<col ; j++) {
					if(outboard[i][j] == "0") {
						outboard[i][j] = "��";
					}
				}
			}
		}
	}

	void PutOutMassage() { //�޼����� ����ϴ� �޼ҵ�
		//�����ϴ� ����

		switch(flag) {
		case 1: 
			System.out.println("���ڸ� �����̽��ϴ�.");
			System.out.println("���ڸ� �����̽��ϴ�.");
			System.out.println("���ڸ� �����̽��ϴ�.");
			System.out.println("���ڸ� �����̽��ϴ�.");
			System.out.println("���ڸ� �����̽��ϴ�.");
			System.out.println("���ڸ� �����̽��ϴ�.");
			System.out.println("���ڸ� �����̽��ϴ�.");
			break;
		case 2:
			System.out.println("���ڸ� ��� ã���̽��ϴ�.");
		}
	}

	void GameStart() {
		//�� �����޼ҵ� �߰�
		while(flag == 0){
			//����¸޼ҵ� �߰�
			OnClick();
			Distinguish(x,y);
			PrintBoard();
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// GameSet ��ü����  ���⼭ GameSet�� GameStrat�� Ȯ���ؼ� �������Ѵ�.
		// ��ü.GameStrat();

		PlayGame sss = new PlayGame();
		sss.Creatboard();
		sss.GameStart();
	}

}