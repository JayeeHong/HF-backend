# DDD Backend 프로젝트 - 헥사고날 아키텍처 분석 보고서

## 프로젝트 개요

본 프로젝트는 Spring Boot 3.5.4와 Java 21을 기반으로 한 DDD(Domain-Driven Design) 및 헥사고날 아키텍처 패턴을 적용한 백엔드 애플리케이션입니다. Spring Modulith를 활용하여 모듈러 아키텍처를 구현하고 있습니다.

## 헥사고날 아키텍처란?

헥사고날 아키텍처(Hexagonal Architecture)는 Alistair Cockburn이 제안한 아키텍처 패턴으로, **포트와 어댑터(Ports and Adapters)** 패턴이라고도 불립니다. 이 아키텍처의 핵심 특징은 다음과 같습니다:

### 헥사고날 아키텍처의 핵심 특징

#### 1. **도메인 중심 설계 (Domain-Centric Design)**
- 비즈니스 로직(도메인)이 아키텍처의 중심에 위치
- 외부 기술이나 프레임워크에 의존하지 않는 순수한 비즈니스 로직
- 도메인은 육각형의 내부(Core Hexagon)에서 보호받음

#### 2. **의존성 역전 원칙 (Dependency Inversion Principle)**
- 내부(도메인)가 외부(인프라)를 정의
- 인터페이스(포트)를 통해 외부 의존성을 추상화
- 도메인 → 인프라가 아닌, 인프라 → 도메인으로 의존성 방향 역전

#### 3. **포트와 어댑터 패턴**
- **포트(Ports)**: 도메인과 외부 세계를 연결하는 인터페이스
  - **Primary Ports (Inbound)**: 애플리케이션으로 들어오는 요청을 처리
  - **Secondary Ports (Outbound)**: 애플리케이션에서 외부로 나가는 요청을 처리
- **어댑터(Adapters)**: 포트의 구체적인 구현체
  - **Primary Adapters**: 외부에서 애플리케이션을 호출 (UI, API)
  - **Secondary Adapters**: 애플리케이션이 외부 시스템을 호출 (DB, 메시징)

#### 4. **테스트 용이성 (Testability)**
- 포트를 통해 Mock 객체로 쉽게 대체 가능
- 단위 테스트에서 외부 의존성 격리
- 통합 테스트에서 실제 어댑터로 교체

#### 5. **기술 독립성 (Technology Independence)**
- 도메인 로직이 특정 기술 스택에 종속되지 않음
- 데이터베이스, 웹 프레임워크, 메시징 시스템 등을 자유롭게 교체 가능
- 비즈니스 로직의 재사용성 증대

### 헥사고날 vs 기존 레이어드 아키텍처 비교

| 특징 | 레이어드 아키텍처 | 헥사고날 아키텍처 |
|------|------------------|-------------------|
| **의존성 방향** | 상위 → 하위 (단방향) | 외부 → 내부 (역전) |
| **중심** | 데이터베이스 중심 | 도메인 중심 |
| **확장성** | 계층 간 강결합 | 포트-어댑터로 느슨한 결합 |
| **테스트** | 하위 계층 의존성 문제 | 포트를 통한 격리 용이 |
| **기술 변경** | 전체적인 영향 | 어댑터만 교체 |

## 헥사고날 아키텍처 구조 분석

### 1. 전체 패키지 구조

```
com.example.dddbackendjy
├── common/                    # 공통 어노테이션 및 인프라
│   ├── UseCase.java          # Application Layer 식별 어노테이션
│   └── DomainService.java    # Domain Layer 식별 어노테이션
└── member/                   # Member 바운디드 컨텍스트
    ├── domain/               # 도메인 계층 (Core/Inner Hexagon)
    ├── application/          # 애플리케이션 계층 (Use Cases)
    ├── infrastructure/       # 인프라스트럭처 계층 (Secondary Adapters)
    └── presentation/         # 프레젠테이션 계층 (Primary Adapters)
```

### 2. 헥사고날 아키텍처 계층별 분석

#### 2.1 도메인 계층 (Domain Layer - Core Hexagon)

**위치**: `com.example.dddbackendjy.member.domain`

- **핵심 엔티티**:
  - `Member.java`: 회원 도메인 엔티티 (Record 타입으로 불변성 보장)
  - `Address.java`: 주소 값 객체
  - `MemberStatus.java`: 회원 상태 열거형

- **도메인 서비스**:
  - `MemberAssembler.java`: 도메인 조립 서비스

- **포트 인터페이스**:
  - `spi/MemberRepository.java`: Secondary Port (아웃바운드 포트)
  - `api/AssembleMember.java`: Primary Port (인바운드 포트)

#### 2.2 애플리케이션 계층 (Application Layer)

**위치**: `com.example.dddbackendjy.member.application`

- **유스케이스 구현**:
  - `CreateMemberUseCase.java`: 회원 생성 유스케이스
  - `GetMemberUseCase.java`: 회원 조회 유스케이스
  - `GetMembersUseCase.java`: 회원 목록 조회 유스케이스
  - `UpdateMemberUseCase.java`: 회원 수정 유스케이스
  - `DeleteMemberUseCase.java`: 회원 삭제 유스케이스

- **특징**:
  - `@UseCase` 어노테이션으로 식별
  - 도메인 계층의 포트 인터페이스에 의존
  - 비즈니스 로직 조합 및 트랜잭션 관리

#### 2.3 인프라스트럭처 계층 (Infrastructure Layer - Secondary Adapters)

**위치**: `com.example.dddbackendjy.member.infrastructure`

- **데이터베이스 어댑터**:
  - `db/MemberRepository.java`: Secondary Port 구현체
  - `db/MemberJpaRepository.java`: JPA Repository 인터페이스
  - `db/model/MemberEntity.java`: JPA 엔티티
  - `db/model/MemberMapper.java`: 도메인-엔티티 매핑

- **특징**:
  - 도메인의 `MemberRepository` 포트를 구현
  - JPA를 통한 데이터 영속성 관리
  - 도메인 객체와 인프라 객체 간 매핑 분리

#### 2.4 프레젠테이션 계층 (Presentation Layer - Primary Adapters)

**위치**: `com.example.dddbackendjy.member.presentation`

- **REST API 어댑터**:
  - `controller/MemberController.java`: REST API 컨트롤러
  - `model/MemberDto.java`: 외부 인터페이스용 DTO
  - `model/AddressDto.java`: 주소 DTO

- **특징**:
  - Spring MVC를 통한 HTTP 요청 처리
  - 애플리케이션 계층의 유스케이스 호출
  - 외부 인터페이스와 내부 도메인 모델 분리

### 3. 본 프로젝트의 헥사고날 아키텍처 구현 분석

#### 3.1 육각형 구조 시각화

```
                    ┌─────────────────────────┐
                    │     Primary Adapters    │
                    │  (외부 → 애플리케이션)    │
                    │                         │
            ┌───────┤    MemberController     ├───────┐
            │       │      (REST API)         │       │
            │       └─────────────────────────┘       │
            │                                         │
            ▼                                         │
    ┌─────────────┐                           ┌─────────────┐
    │  Primary    │                           │ Secondary   │
    │   Ports     │        ┌─────────┐        │   Ports     │
    │ (Inbound)   │◄──────►│ DOMAIN  │◄──────►│ (Outbound)  │
    │             │        │  CORE   │        │             │
    │AssembleMember│        │ Member  │        │MemberRepo   │
    └─────────────┘        │Address  │        │ Interface   │
            ▲               │ Status  │        └─────────────┘
            │               └─────────┘                │
            │                   ▲                     │
            │           ┌───────────────┐             ▼
            │           │ Application   │     ┌─────────────┐
            │           │    Layer      │     │ Secondary   │
            └───────────┤  Use Cases    │     │  Adapters   │
                        │   (@UseCase)  │     │(애플리케이션→외부)│
                        └───────────────┘     │             │
                                              │MemberRepository│
                                              │ (JPA Impl)    │
                                              └─────────────┘
```

#### 3.2 의존성 방향 분석

```
외부 시스템 ──→ Primary Adapter ──→ Application Layer ──→ Domain Core
                                           │
                                           ▼
                                    Secondary Port Interface
                                           ▲
                                           │
                              Secondary Adapter ──→ 외부 시스템
```

**핵심 원칙 준수 현황**:

✅ **의존성 역전 원칙**:
- 도메인 계층(`Member.java:5`)이 인프라스트럭처에 의존하지 않음
- `MemberRepository` 인터페이스를 도메인(`member.domain.spi:7`)에 정의
- 인프라 계층에서 포트 구현(`infrastructure.db.MemberRepository:10`)

✅ **포트 구현 현황**:

**Primary Ports (Inbound)**:
- `AssembleMember` 인터페이스(`member.domain.api:5`) - 도메인 진입점
- 애플리케이션 유스케이스들이 실제 비즈니스 로직 진입점 역할

**Secondary Ports (Outbound)**:
- `MemberRepository` 인터페이스(`member.domain.spi:7`) - 데이터 액세스 추상화

**Primary Adapters**:
- `MemberController`(`presentation.controller:13`) - HTTP/REST 어댑터
- Spring MVC를 통한 외부 요청 처리

**Secondary Adapters**:
- `MemberRepository` 구현체(`infrastructure.db:10`) - JPA 어댑터
- `MemberMapper`(`infrastructure.db.model:MemberMapper`) - 도메인-엔티티 변환

#### 3.3 도메인 순수성 보장

✅ **도메인 모델 특징**:
- `Member` 엔티티(`domain.Member:5`) - Record 타입으로 불변성 보장
- `Address` 값 객체(`domain.Address`) - 도메인 개념의 명시적 표현
- `MemberStatus` 열거형(`domain.MemberStatus`) - 타입 안전성 제공
- 프레임워크 어노테이션이나 의존성 완전 배제

#### 3.4 애플리케이션 계층의 역할

✅ **유스케이스 구현 패턴**:
- `@UseCase` 어노테이션(`common.UseCase:14`)으로 역할 명시
- 각 유스케이스는 단일 비즈니스 기능 담당
- 도메인 객체 조합 및 트랜잭션 경계 관리
- 포트 인터페이스를 통한 외부 의존성 추상화

**예시**: `CreateMemberUseCase.execute()`(`application.CreateMemberUseCase:23`)
1. 도메인 객체 생성 및 조합
2. 비즈니스 규칙 적용 (`generateMemberNumber()`)
3. 포트를 통한 영속성 처리
4. DTO 변환 후 반환

### 4. 헥사고날 아키텍처 적용으로 얻은 구체적 장점

#### 4.1 **비즈니스 로직 보호 및 순수성**
- **도메인 중심**: `Member`, `Address` 등 순수 도메인 객체로 비즈니스 규칙 표현
- **기술 독립성**: 도메인 로직이 Spring, JPA 등 프레임워크에 의존하지 않음
- **불변성 보장**: Record 타입 사용으로 상태 변경에 대한 안전성 제공

#### 4.2 **유연한 확장성 (Adapter Pattern의 힘)**
```java
// 새로운 어댑터 추가 시 기존 코드 변경 없음
// 예: REST API → GraphQL API 추가
@Component
public class MemberGraphQLController {
    private final CreateMemberUseCase createMemberUseCase; // 동일한 유스케이스 재사용
}

// 예: JPA → MongoDB 변경
@Component  
public class MemberMongoRepository implements MemberRepository {
    // 도메인 포트 인터페이스 구현으로 완전 교체 가능
}
```

#### 4.3 **테스트 용이성 극대화**
- **단위 테스트**: 포트를 Mock으로 대체하여 도메인 로직만 테스트
- **통합 테스트**: 실제 어댑터로 교체하여 전체 플로우 테스트
- **계약 테스트**: 포트 인터페이스 기준으로 어댑터 호환성 검증

#### 4.4 **관심사의 명확한 분리**
- **프레젠테이션**: HTTP 요청/응답 처리만 담당
- **애플리케이션**: 비즈니스 플로우 조합 및 트랜잭션 관리
- **도메인**: 순수 비즈니스 로직과 정책
- **인프라**: 외부 시스템과의 기술적 통신

#### 4.5 **모듈화와 바운디드 컨텍스트**
- **Spring Modulith**: 바운디드 컨텍스트 간 명확한 경계
- **패키지 구조**: 도메인별로 독립적인 헥사고날 구조 반복
- **의존성 관리**: 모듈 간 순환 의존성 방지

### 5. 헥사고날 아키텍처 관점에서의 개선 제안

#### 5.1 **도메인 이벤트 도입**
```java
// 도메인 이벤트를 통한 사이드 이펙트 분리
public record MemberCreatedEvent(String memberId, String email) {}

// 애플리케이션 계층에서 이벤트 발행
@UseCase
public class CreateMemberUseCase {
    private final DomainEventPublisher eventPublisher;
    
    public MemberDto execute(CreateMemberRequest request) {
        Member member = // ... 회원 생성
        Member saved = memberRepository.save(member);
        
        // 도메인 이벤트 발행으로 사이드 이펙트 분리
        eventPublisher.publish(new MemberCreatedEvent(saved.id(), saved.email()));
        return // ... DTO 변환
    }
}
```

#### 5.2 **도메인 서비스 강화**
```java
// 복잡한 비즈니스 로직을 도메인 서비스로 이동
@DomainService
public class MemberDomainService {
    public boolean canDeleteMember(Member member) {
        // 복잡한 삭제 가능 여부 판단 로직
        return member.status() == MemberStatus.INACTIVE 
            && member.hasNoPendingTransactions();
    }
    
    public MemberNumber generateUniqueMemberNumber(MemberRepository repository) {
        // 고유 회원번호 생성 로직
    }
}
```

#### 5.3 **포트 확장을 통한 외부 시스템 통합**
```java
// 새로운 Secondary Port 추가
public interface NotificationService {
    void sendWelcomeEmail(String email, String memberName);
    void sendSms(String phoneNumber, String message);
}

// 이메일 어댑터 구현
@Component
public class EmailNotificationAdapter implements NotificationService {
    // 실제 이메일 서비스 구현
}
```

#### 5.4 **도메인 예외 및 검증 강화**
```java
// 도메인 특화 예외
public class MemberDomainException extends RuntimeException {
    public static MemberDomainException memberNotFound(String memberId) {
        return new MemberDomainException("Member not found: " + memberId);
    }
    
    public static MemberDomainException invalidEmail(String email) {
        return new MemberDomainException("Invalid email format: " + email);
    }
}

// 도메인 객체 내 검증 로직 강화
public record Member(String id, String email, /* ... */) {
    public Member {
        validateEmail(email);
        validateId(id);
    }
    
    private void validateEmail(String email) {
        if (!EmailValidator.isValid(email)) {
            throw MemberDomainException.invalidEmail(email);
        }
    }
}
```

#### 5.5 **다중 어댑터 지원**
```java
// 다양한 Primary Adapter 지원
@RestController  // REST API Adapter
public class MemberRestController { ... }

@Component      // GraphQL Adapter  
public class MemberGraphQLResolver { ... }

@EventListener  // Event-driven Adapter
public class MemberEventController { ... }
```

## 기술 스택

- **Framework**: Spring Boot 3.5.4, Spring Modulith 1.2.4
- **Language**: Java 21
- **Database**: H2 (개발환경), JPA/Hibernate
- **Build**: Gradle
- **Architecture**: DDD + Hexagonal Architecture

## 헥사고날 아키텍처 적용 효과 및 결론

### 📈 **아키텍처 성숙도 평가**

| 헥사고날 원칙 | 구현 수준 | 평가 |
|-------------|----------|------|
| **도메인 중심성** | ⭐⭐⭐⭐⭐ | 순수한 도메인 모델, 프레임워크 독립성 |
| **의존성 역전** | ⭐⭐⭐⭐⭐ | 포트를 통한 완벽한 의존성 제어 |
| **포트-어댑터** | ⭐⭐⭐⭐⚪ | Primary/Secondary 포트 명확, 다중 어댑터 지원 여지 |
| **테스트 용이성** | ⭐⭐⭐⭐⭐ | Mock을 통한 격리 테스트 가능 |
| **확장성** | ⭐⭐⭐⭐⭐ | 새로운 어댑터 추가 시 기존 코드 영향 없음 |

### 🎯 **비즈니스 가치**

1. **빠른 프로토타이핑**: 도메인 로직과 기술 구현을 분리하여 비즈니스 규칙 검증 우선
2. **기술 스택 진화**: JPA → NoSQL, REST → GraphQL 등 기술 변경 시 위험 최소화  
3. **팀 개발 효율성**: 계층별 명확한 책임으로 병렬 개발 및 전문화 가능
4. **품질 보증**: 포트를 통한 계약 기반 개발로 결함 조기 발견

### 🚀 **헥사고날 아키텍처의 진정한 가치**

본 프로젝트는 단순히 "잘 구조화된 코드"를 넘어서 **진화 가능한 시스템**을 구현했습니다:

- **도메인 불변성**: 비즈니스 로직이 기술 변화에 영향받지 않음
- **포트 기반 확장**: 새로운 요구사항을 기존 코드 변경 없이 추가 가능
- **검증된 패턴**: DDD와 결합한 헥사고날 아키텍처로 복잡도 관리
- **Spring Modulith 활용**: 바운디드 컨텍스트를 물리적으로 분리하여 모듈러 모놀리스 실현

**결론적으로**, 이 프로젝트는 헥사고날 아키텍처의 핵심 가치인 **"비즈니스 로직의 보호"**와 **"기술적 유연성"**을 성공적으로 달성하고 있으며, 장기적인 유지보수성과 확장성을 확보한 우수한 아키텍처 사례입니다.