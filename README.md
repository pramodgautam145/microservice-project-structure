Created GKE cluster in GCP.
 
 
 
 
 
 Microservice consumed another microservice which connected with mongodb

 Basic commond 
 docker push
docker run -e SPRING_PROFILES_ACTIVE=local -p 8080:8080 microservice:latest
docker build -t microservice:latest .
docker push microservice:latest
docker tag microservice:latest pramodgautam145/microservice:latest
docker push pramodgautam145/microservice:latest
docker build -t microservicedb:latest .
docker tag microservicedb:latest pramodgautam145/microservicedb:latest
docker push pramodgautam145/microservicedb:latest

deployment steps-
deployment in gke
create gke cluster
install gcloud sdk
go to bin path - C:\Users\AppData\Local\Google\Cloud SDK\google-cloud-sdk\bin
connect gke cluster


kubectl apply -f C:\Users\Downloads\deployment\mongo.yml
kubectl apply -f C:\Users\Downloads\deployment\deployment.yml
kubectl create configmap logstash-config --from-file=logstash.yml=./logstash/config/logstash.yml
kubectl create configmap logstash-pipeline --from-file=./logstash/pipeline

kubectl exec -it <pod_name> -- /bin/bash
kubectl get pods --all-namespaces
kubectl get svc --all-namespaces
kubectl expose deploy microservice-app --port=8093 --dry-run=client -oyaml>microservice-app.yaml


output {
  elasticsearch {
    hosts => ["http://elasticsearch-service:9200"]
    index => "my_index"
  }
}

for  Local
output {
  elasticsearch {
    hosts => ["elasticsearch:9200"]
  }
}

 
 
 
 
 
 

