# Sklep internetowy – aplikacja Spring Boot

To prosta, ale solidna aplikacja sklepu internetowego zbudowana przy użyciu Spring Boot i Maven, zgodnie z zasadami Test-Driven Development (TDD). Aplikacja umożliwia użytkownikom przeglądanie produktów, dodawanie ich do koszyka i symulowanie płatności za pośrednictwem interfejsu API PayU (obecnie przy użyciu przykładowych wymian żądań).

## Funkcje

Katalog produktów – Wyświetlanie dostępnych produktów.

Koszyk – Dodawanie i zarządzanie produktami w koszyku.

Kasa i płatność – Symulowany przepływ płatności za pomocą interfejsu API PayU.

## Stos technologiczny

Spring Boot – Framework backendu.

Maven – Zarządzanie zależnościami.

JUnit (Jupiter) i AssertJ – Testowanie jednostkowe.

Integracja i testy E2E – Zapewnianie niezawodności systemu.

GitHub Actions – Automatyzacja CI.

Docker i Docker Compose – Wdrożenie kontenerowe.

AWS EC2 – Docelowe środowisko wdrożenia.

## CI/CD Pipeline

Projekt obejmuje GitHub Actions i skrypty Docker CI do automatyzacji kompilacji i testowania. Aplikacja jest zaprojektowana do działania na instancji AWS EC2 przy użyciu kontenerów Docker.

## Lokalne uruchomienie

1. Sklonuj repozytorium 
    `
    git clone https://github.com/bCharN/
    `
    `
    cd my_ecommerce
    `



2. Wybuduj i uruchom za pomocą Dockera
    
    `
    docker compose -f ./ci/compose/build_compose.yaml up
    `
lub
    `
    docker build -f ./ci/dockerfiles/maven_build.Dockerfile
    `
    `
    docker run my_ecommerce_1.0-snapshot
    `

3. Dostęp do aplikacji pod adresem `http://localhost:8080/`

## Testowanie

    
    docker compose -f ./ci/compose/compose.yaml --profile test up
    


