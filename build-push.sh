#!/bin/bash
aws ecr get-login-password --region eu-central-1 | docker login --username AWS --password-stdin 864313221502.dkr.ecr.eu-central-1.amazonaws.com/aws-demo &&
docker build -t aws-demo . &&
docker tag aws-demo:latest 864313221502.dkr.ecr.eu-central-1.amazonaws.com/aws-demo:latest &&
docker push 864313221502.dkr.ecr.eu-central-1.amazonaws.com/aws-demo:latest


