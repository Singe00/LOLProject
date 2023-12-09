# LOL Project
리그 오브 레전드 api를 활용한 전적 게임 및 승률 예측 딥러닝 모델을 활용한 게임 분석 웹 서비스
<br/>

[lpgg.site](http://lpgg.site/)

<br/>

<h1>Develope</h1>

<br/>

> 풀스택 개발 (Full Stack)
  <p>Front  : HTML (SSR), JavaScript</p>
  
  <p>Back   : Java, Spring Boot, JPA</p>
  
  <p>DB     : MySQL</p>

  <p>etc    : AWS EC2, 가비아 도메인</p>
  <br/>

  
> 주요 기능
  <p align="left">
    <img loading='lazy' decoding='async' src="https://user-images.githubusercontent.com/103260185/276583506-24120780-ebfb-4428-bd74-506377ce2530.png" width="650" height="350">
  </p>
  <br/>
  <p align="left">
    <img loading='lazy' decoding='async' src="https://user-images.githubusercontent.com/103260185/276583769-5fcdbad1-08b8-4662-8cad-9f2ed009241b.png" width="650" height="350">
  </p>
  <br/>
  <p align="left">
    <img loading='lazy' decoding='async' src="https://user-images.githubusercontent.com/103260185/276583960-f7f24406-0eed-4f68-83b1-506fef6dc8a5.png" width="650">
  </p>
  <br/>
  <p align="left">
    <img loading='lazy' decoding='async' src="https://user-images.githubusercontent.com/103260185/276584031-9cba9763-cad9-486f-bbda-5d1084b56f89.png" width="650">
  </p>
  <br/>
  <p align="left">
    <img loading='lazy' decoding='async' src="https://user-images.githubusercontent.com/103260185/276584123-8833b337-4346-44ef-97d5-ce448180e3fe.png" width="650">
  </p>
  <br/>
  <p align="left">
    <img loading='lazy' decoding='async' src="https://user-images.githubusercontent.com/103260185/276584349-1b49417a-ea21-4c3d-abbe-9355d8af81b3.png" width="650" height="350">
  </p>
  <br/>
  <p align="left">
    <img loading='lazy' decoding='async' src="https://user-images.githubusercontent.com/103260185/285489387-e94f0537-c9c1-4bb3-a02c-a6d1ac7ccd0a.png" width="650" height="350">
  </p>
  <br/>
  <p align="left">
    <img loading='lazy' decoding='async' src="https://user-images.githubusercontent.com/103260185/285489459-42a9ec3a-965f-46a3-883a-83d1dc27f216.png" width="650" height="350">
  </p>
  <p align="left">
    <img loading='lazy' decoding='async' src="https://user-images.githubusercontent.com/103260185/285489857-0cf4ce12-f946-4ea7-a33d-5e7f1621c5f6.png" width="650"
  </p>


<br/>

<h1>AI Develope</h1>
> https://github.com/Singe00/LOL-Winning-Predict

<br/>

<h1>History</h1>

#### 1. API 분석✔
- 날짜 : 2023.02.01(수) ~ 2023.03.03(금)
- 내용 : RIOT API 데이터 분석 및 수집 코드 구축 / https://github.com/Singe00/LOL-Winning-Predict/blob/main/lolProject.ipynb

#### 2. 데이터셋 수집✔
- 날짜 : 2023.03.05(일) ~ 진행중
- 내용 : RIOT API로 매치 데이터 수집 (마스터랭크 이상 플레이어의 분당 인게임 매치 데이터 / 수집한 데이터 총 2,000,000회 게임)

#### 3. 딥러닝 모델 학습
- 날짜 : 2023.03.15(수) ~ 진행ㅈ중
- 내용 : 수집한 매치 데이터로 최적의 딥러닝 모델 학습 중 (목표치 / Accuracy : 95%, 현 Accuracy : 83%)

#### 4. AWS 서버 배포✔
- 날짜 : 2023.09.01(일) ~ 진행중
- 내용 : AWS EC2를 사용하여 서버 배포 완료 및 유지보수 중
  
#### 5. 전적 검색 기능 구축✔
- 날짜 : 2023.08.01(일) ~ 2023.10.03(화)
- 내용 : RIOT API로 전적 검색 기능 개발 완료

#### 6. 랭킹 탭 구축✔
- 날짜 : 2023.10.01(일) ~ 2023.10.02(월)
- 내용 : RIOT API로 소환사 랭킹 및 추가정보 수집 후 시각화 완료

#### 7. 챔피언 인게임 데이터셋 수집✔
- 날짜 : 2023.11.01(일) ~ 2023.11.21(화)
- 내용 : RIOT API로 인게임 데이터셋 수집해 챔피언 별 데이터 전처리 작업 후 통계화 작업 완료

#### 8. 챔피언 검색 기능 구축
- 날짜 : 2023.10.11(토) ~ 진행중
- 내용 : 챔피언 정보 및 통계 자료 검색 기능 구현 및 UI 디자인 중

# 이슈
- 2023.10.13(금)<br/>
  AWS EC2 프리티어의 cpu, ram 가용량 한계로 인해 서버다운이 주기적으로 발생함<br/>
- 2023.11.22(수)<br/>
  Riot Games의 리그 오브 레전드 '소환사명' → 'RiotId + tagLine' 정책으로 인해 소환사 명 검색 기능 수정 (Front 디자인, API설계)<br/>
  
  
