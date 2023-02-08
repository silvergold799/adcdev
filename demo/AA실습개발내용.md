***
# Git (GKE 클러스터 생성시 자동설치 되어 있다)
***

* Github에서 프로젝트 소스 파일을 클라우드 Repository로 가져온다.
```sh
git clone [Repository 주소 ex)https://github.com/aaa/bbb.git] [다운로드경로]

ex) git clone https://github.com/silvergold799/adcdev.git ./adcdev
```

* Repository에 직접 소스  다운 받기
```sh
git clone https://github.com/silvergold799/adcdev.git ./adcdev
```

* gradle 빌드
```sh
소스 위치로 이동
cd adcdev/demo
chmod +x ./gradlew
./gradlew clean build
```

* JDK 버전 오류 시 수동으로 JAVA_HOME 변경
```sh
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64 
```

* Docker 빌드 이미지 만들기
```sh
docker build --build-arg JAR_FILE=build/libs/*.war -t parkkihong/silvergold799:v1.3 .
```

* dockerfile 위치가 현재 경로가 아닐경우 제일 마지막에 경로 설정 
```sh
docker build --build-arg JAR_FILE=build/libs/*.war -t parkkihong/silvergold799:v1.3 ./adcdev/demo/
```


* Docker 실행
```sh
docker run -d -p 8080:8080 --name docker_app adcapp
```

* Docker 컨테이너 정상적으로 서비스 되는지 확인
```sh
curl http://127.0.0.1:8080
```

* docker 이미지 push
```sh
docker push parkkihong/silvergold799:v1.3
```

* Docker 명령어
```sh
- 컨테이너 실행
docker start 컨테이너id
- 컨테이너 중지
docker stop 컨테이너id
- 컨테이너 삭제
docker rm 컨테이너id
- 이미지 삭제
docker rmi 이미지id
```

***
# Istio
***

* Istio 다운로드
```sh
curl -L https://istio.io/downloadIstio | ISTIO_VERSION=1.16.2 TARGET_ARCH=x86_64 sh -
```

* Istio 디렉토리로 이동
```sh
cd istio-1.16.2
```
* istioctl경로에 클라이언트를 추가합니다 (Linux 또는 macOS).
```sh
export PATH=$PWD/bin:$PATH
```


* Istio 설치
```sh
istioctl install --set profile=demo -y
✔ Istio core installed
✔ Istiod installed
✔ Egress gateways installed
✔ Ingress gateways installed
✔ Installation complete
```

* Istio  Envoy 사이드카 프록시를 자동으로 삽입하도록 네임스페이스 레이블을 추가
```sh
kubectl label namespace default istio-injection=enabled
✔ namespace/default labeled

라벨확인
kubectl get namespaces --show-labels
```

* 애플리케이션 배포 (기존 배포되어 있으면 삭제하고 재배포 해야 된다.)
```sh
kubectl apply -f deployment.yaml
```
이전 명령을 다시 실행 하고 다음 단계로 이동하기 전에 모든 포드가 READY 2/2및 STATUS 를 보고할 때까지 기다립니다 . Running플랫폼에 따라 몇 분 정도 걸릴 수 있습니다. 

* 애플리케이션을 Istio 게이트웨이와 연결
```sh
kubectl apply -f istio-ingress-gateway.yaml
✔ gateway.networking.istio.io/gateway-pjname created
✔ virtualservice.networking.istio.io/virtual-service-pjname created
```

* 구성에 문제가 없는지 확인
```sh
istioctl analyze
✔ No validation issues found when analyzing namespace: default.
```

* 대시보드 설치하기
```sh
kubectl apply -f samples/addons
kubectl rollout status deployment/kiali -n istio-system
✔ Waiting for deployment "kiali" rollout to finish: 0 of 1 updated 
✔ replicas are available...
✔ deployment "kiali" successfully rolled out
```

* Kiali 대시보드 보기
```sh
istioctl dashboard kiali
```

* istio addons 외부접속설정
* 애드온을 노출하도록 도메인을 설정
* 공식 문서를 참고하여 외부 접속을 설정합니다.
* https://istio.io/latest/docs/tasks/observability/gateways/#option-2-insecure-access-http
```sh
export INGRESS_DOMAIN="istioaddons.com"
```

* 도메인이 없는 경우 제공된 IP 주소로 자동 확인되는 도메인을 사용
```sh
export INGRESS_HOST=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
export INGRESS_DOMAIN=${INGRESS_HOST}.nip.io

```


# Istio
istio operator reference
- https://istio.io/latest/docs/reference/config/istio.operator.v1alpha1/

- Source IP 전달하기 참조
- https://istio.io/latest/docs/tasks/security/authorization/authz-ingress/#source-ip-address-of-the-original-client

GCP 의 경우 아래와 같이 적용
```sh
kubectl patch svc istio-ingressgateway -n istio-system -p '{"spec":{"externalTrafficPolicy":"Local"}}'
```

- Kiali 대시보드
```sh
istioctl dashboard kiali
```

- Google 쿠버네티스 엔진(GKE) 
- 범위는 고정되어 있지 않으므로 gcloud container clusters describe사용할 범위를 결정하려면 명령을 실행
```sh
gcloud container clusters describe XXXXXXX --zone=XXXXXX | grep -e clusterIpv4Cidr -e servicesIpv4Cidr
✔ clusterIpv4Cidr: 10.4.0.0/14
✔ servicesIpv4Cidr: 10.7.240.0/20
```



# Jenkins
클라우드 Repository 소스파일을 War 또는 jar로 빌드 한다.





# Docker
```sh
docker ps
docker container ls -a
docker tag adcapp adcapp:v1.1
docker tag adcapp parkkihong/silvergold799:v1.1
docker push parkkihong/silvergold799:v1.1
docker pull parkkihong/silvergold799:v1.1

docker build --build-arg JAR_FILE=build/libs/*.war -t adcapp .

도커 컨테이너 안으로 진입
docker exec -it adcapp /bin/sh

```


# Kubernetes

* 현재 활성화된 네임스페이스 확인은 config view 를 통해서 할 수 있습니다.
```sh
kubectl config view | grep namespace
kubectl describe pod {POD_ID} -n {namespace}
kubectl get - 리소스 나열
kubectl describe - 리소스에 대한 자세한 정보 표시
kubectl logs - 포드의 컨테이너에서 로그를 인쇄합니다.
kubectl exec - 포드의 컨테이너에서 명령 실행 

pod 생성
kubectl apply -f simple-pod.yaml

pod 삭제
kubectl delete -f simple-pod.yaml

deployment 생성
kubectl apply -f simple-deployment.yaml --record

deployment  확인
kubectl get deployments

deployment history 확인
kubectl rollout history deployment echo

ifconfig

컨테이너 안에 직접 명령어 보내기 /bin/sh  curl 안됨 wget 사용
kubectl exec -it adc-dp-79564f54d8-t8qm9 -- wget 10.92.2.10:8080

pod 내 container 접속
kubectl create deployment deploy-adcapp --image=adcapp

namespace label 설정
kubectl label namespace istio istio-injection=enabled

namespace label 확인
kubectl get namespaces --show-labels
```



- 대시보드 istio side car inject 활성화
- 다음의 3가지 deployment 를 edit 한다
```sh
kubectl -n istio-system edit deployment.apps/prometheus
kubectl -n istio-system edit deployment.apps/grafana
kubectl -n istio-system edit deployment.apps/kiali
```


