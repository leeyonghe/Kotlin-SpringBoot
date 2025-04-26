# Kotlin Spring Boot Project / 코틀린 스프링 부트 프로젝트

A Spring Boot application built with Kotlin, featuring a modern web stack with MariaDB database integration.
코틀린으로 구축된 스프링 부트 애플리케이션으로, MariaDB 데이터베이스와 통합된 현대적인 웹 스택을 제공합니다.

## 🚀 Tech Stack / 기술 스택

- **Framework**: Spring Boot 3.4.0
- **Language**: Kotlin / 코틀린
- **Database**: MariaDB 10.6
- **Template Engine**: Thymeleaf
- **Build Tool**: Gradle
- **Containerization**: Docker & Docker Compose

## 📋 Prerequisites / 필수 요구사항

- Java 17 or higher / Java 17 이상
- Docker and Docker Compose
- Gradle

## 🛠️ Project Structure / 프로젝트 구조

```
.
├── src/                    # Source code / 소스 코드
├── gradle/                 # Gradle wrapper files
├── screenshot/            # Project screenshots / 프로젝트 스크린샷
├── build.gradle           # Project dependencies / 프로젝트 의존성
├── docker-compose.yml     # Docker configuration / 도커 설정
├── Dockerfile            # Application container definition / 애플리케이션 컨테이너 정의
└── run.sh                # Run script / 실행 스크립트
```

## 🔧 Dependencies / 의존성

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Web Services
- MariaDB Java Client
- Lombok
- JSON Simple
- Spring Boot Test

## 🚀 Getting Started / 시작하기

1. **Clone the repository / 저장소 복제**
   ```bash
   git clone [repository-url]
   cd Kotlin-SpringBoot
   ```

2. **Build the project / 프로젝트 빌드**
   ```bash
   ./gradlew build
   ```

3. **Run with Docker Compose / Docker Compose로 실행**
   ```bash
   docker-compose up -d
   ```

The application will be available at `http://localhost:8080`
애플리케이션은 `http://localhost:8080`에서 접근 가능합니다.

## ⚙️ Configuration / 설정

### Database Configuration / 데이터베이스 설정
- Host: localhost
- Port: 3306
- Database: allra
- Username: allra
- Password: test123~!

### Environment Variables / 환경 변수
- `SPRING_PROFILES_ACTIVE=prod`
- `TZ=Asia/Seoul`

## 📦 Docker Setup / 도커 설정

The project includes: / 프로젝트는 다음을 포함합니다:
- Application container (`allra_be`) / 애플리케이션 컨테이너
- MariaDB container (`mariadb`) / MariaDB 컨테이너

### Ports / 포트
- Application: 8080
- Database: 3306

## 🧪 Testing / 테스트

Run tests using: / 다음 명령어로 테스트를 실행합니다:
```bash
./gradlew test
```
