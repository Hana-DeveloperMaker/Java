# 👨‍💻 개발자 키우기 DeveloperMaker

신입 개발자가 되고 싶은 주인공의 **최종 면접까지의 여정**을 시뮬레이션으로 조작하는 게임. **사용자가 선택한 선택지**에 따라 사용자의 면접 결과가 결정되고, 이를 바탕으로 사용자의 면접 스타일을 분석해줍니다. 또한 면접 복장을 선택할 수 있는 **'면접 옷 입히기'** 미니 게임을 통해 사용자의 면접 복장 스타일도 파악할 수 있는 Java 기반 게임입니다.

## 팀원 소개

|            <a href="https://github.com/jimin0304">박지민</a>            |            <a href="https://github.com/twogarlic">임하늘</a>            |            <a href="https://github.com/address0">주소영</a>            |        <a href="https://github.com/hurdong">허동민 (팀장)</a>         |
| :---------------------------------------------------------------------: | :---------------------------------------------------------------------: | :--------------------------------------------------------------------: | :-------------------------------------------------------------------: |
| <img src="https://github.com/jimin0304.png" width="100%" height="100%"> | <img src="https://github.com/twogarlic.png" width="100%" height="100%"> | <img src="https://github.com/address0.png" width="100%" height="100%"> | <img src="https://github.com/hurdong.png" width="100%" height="100%"> |

---

## 기술 스택

- **Java**: CLI를 활용한 사용자 커맨드 관리 및 파일 입출력을 활용한 사용자 데이터 저장 및 조회

---

## 파일 구조

```
Java/
└── hanaJava/
    ├── .idea/                         # IntelliJ 프로젝트 설정 파일
    ├── data/                          # 게임 사용자들의 더미 데이터 저장 공간
    │   └── users.json                 # 사용자 정보를 담은 JSON 파일
    ├── src/
    │   └── main/
    │       ├── java/                  # 실제 게임 로직이 구현된 Java 소스 코드
    │       │   └── com/
    │       │       └── developermaker/
    │       │           └── Main.java # 프로그램 진입점 (main 메서드 포함)
    │       └── resources/            # 게임 내에서 사용되는 이미지 등의 리소스 저장 공간
    ├── target/                        # 빌드 결과물이 저장되는 디렉토리 (자동 생성)
    ├── .gitignore                     # Git에서 제외할 파일/디렉토리 설정
    ├── pom.xml                        # Maven 프로젝트 의존성 및 빌드 설정 파일
```

---

## 실행 방법

## ▶️ 실행 방법

### ✅ IntelliJ IDEA에서 실행

1. IntelliJ에서 프로젝트 폴더(`Java/hanaJava`)를 엽니다.
2. 프로젝트 트리에서 `src/main/java/com/developermaker/Main.java` 파일을 엽니다.
3. `main` 메서드 왼쪽에 있는 ▶️ 버튼을 눌러 **Run 'Main.main()'** 을 선택하면 실행됩니다.

---

### ✅ Eclipse에서 실행

1. Eclipse를 실행한 후, `File > Import > Existing Maven Projects` 선택
   - 루트 디렉토리로 `Java/hanaJava` 선택 후 Finish
2. 패키지 탐색기(Package Explorer)에서 `src/main/java/com/developermaker/Main.java` 경로를 찾습니다.
3. `Main.java`를 우클릭 → **Run As > Java Application**을 선택하면 실행됩니다.

> 💡 Eclipse에서 Maven 설정 없이도 작동하지만, 필요 시 `pom.xml`을 인식하도록 Maven 프로젝트로 임포트하세요.

---

### ✅ 터미널에서 실행 (JDK 설치 시)

```bash
# 1. 컴파일
cd Java/hanaJava/src/main/java
javac com/developermaker/Main.java

# 2. 실행
java com.developermaker.Main
```

---

⚠️ **주의:**

- `users.json` 또는 이미지 리소스를 상대 경로로 불러올 경우, 실행 위치에 따라 경로가 달라질 수 있으니 확인이 필요합니다.

## 주요 기능

- **📖 스토리 기반 시나리오**: 사용자는 다양한 면접 전후 상황에서 알맞은 선택지를 선택하여 면접 점수를 획득할 수 있습니다.
- **🎨 옷입히기 GUI**: 사용자는 hair / top / bottom / shoes 카테고리별 의상을 선택할 수 있고, 선택한 복장은 이미지(outfit.png)로 저장됩니다. 복장 조합에 따라 특별 점수 부여 및 easter egg , 다른 면접 대사 결과가 발생할 수 있습니다.
- **📊 점수 시스템**: PASSION, EXCELLENCE, RESPECT, OPENNESS, WITH_CUSTOMER 항목을 점수화하여 총합에 따라 면접 결과가 결정됩니다.
- **🖼 하루 되돌아보기 - Carousel**: 유저의 하루 선택 결과를 이미지로 보여주는 회고 프레임으로, 좌우 이동으로 선택 장면을 회고 할 수 있습니다.

## 💾 저장 정보
- 유저 데이터는 JSON 형식으로 저장되며, 닉네임 기준으로 로딩됩니다.
- 동일 닉네임 존재 시 기록 불러오기/삭제/재시작 선택 가능
- 점수 및 선택 결과는 모두 User 객체에 저장됩니다.
