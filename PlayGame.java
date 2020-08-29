package 지뢰찾기;
public class PlayGame extends GameSet{

	int flag = 0; //결과를 판단하는 변수
	int x = 0, y = 0;
	int re; //판별할때 while문에서 조건으로 쓰인다.
	void OnClick() { 
		//속판: 클릭한 곳에서부터 숫자까지 퍼지기
		//겉판: 확인되는 배열부분 겉판[i][j] = 속판[i][j]
		//원하는 좌료를 클릭 하면 컴퓨터상에서는 -1씩 더해줘야 한다.

		System.out.println("x좌표 범위 (1~"+row+")중에 임의의 x좌표를 입력하세요");
		x = sc.nextInt();
		System.out.println("y좌표 범위 (1~"+col+")중에 임의의 y좌표를 입력하세요");
		y = sc.nextInt();
		Distinguish(x, y);
	}

	void Distinguish(int x, int y) { //판별하는 메소드 OnClick에서 좌표값을 받아서 실행.
		//클릭좌표와 지뢰좌표를 매개변수로 받고
		//클릭좌표 == 지뢰좌표인지 확인
		//참일경우: flage= 1; 거짓을 경우: 바꾸지 않음 ; NumOfMine = 남은 ■ 개수일때 : flag = 2;
		//GetMassage()로 경우의 결과를 출력;

		int numofblacksquare = 0;
		for(int i=0; i<row ; i++){
			for(int j=0;j<col;j++) {
				if(outboard[i][j] == "■")
					numofblacksquare++;
			}
		}

		if(numofmine == numofblacksquare)  {
			flag = 2;
			PutOutMassage();
		}else if(inboard[x-1][y-1] == "*") { // 아직 찾을 지뢰가 남아있는 경우
			flag = 1;
			outboard[x-1][y-1] = "*";
			PutOutMassage(); 
		}else if(Integer.parseInt(inboard[x-1][y-1]) >= 1) {
			outboard[x-1][y-1] = inboard[x-1][y-1];
			//다음좌표요구! 아무것도 쓰지 않으면 자연스럽게 OnClick으로 갈 수 있다?           //
		}else {                     //우측하단

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
						// 이 부분이 없으면 1이상의 숫자들이 연쇄적으로 밝혀지지 않는다.
						//처음부터 적용할 수 는 없다. 왜냐하면 아직 까만 사각형만 있기 때문에 문자형으로 비교를 해야한다.
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
						outboard[i][j] = "□";
					}
				}
			}
		}
	}

	void PutOutMassage() { //메세지를 출겨하는 메소드
		//종료하는 역할

		switch(flag) {
		case 1: 
			System.out.println("지뢰를 밟으셨습니다.");
			System.out.println("지뢰를 밟으셨습니다.");
			System.out.println("지뢰를 밟으셨습니다.");
			System.out.println("지뢰를 밟으셨습니다.");
			System.out.println("지뢰를 밟으셨습니다.");
			System.out.println("지뢰를 밟으셨습니다.");
			System.out.println("지뢰를 밟으셨습니다.");
			break;
		case 2:
			System.out.println("지뢰를 모두 찾으셨습니다.");
		}
	}

	void GameStart() {
		//판 생성메소드 추가
		while(flag == 0){
			//판출력메소드 추가
			OnClick();
			Distinguish(x,y);
			PrintBoard();
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// GameSet 객체생성  여기서 GameSet은 GameStrat를 확장해서 만들어야한다.
		// 객체.GameStrat();

		PlayGame sss = new PlayGame();
		sss.Creatboard();
		sss.GameStart();
	}

}