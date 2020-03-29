aws ecr get-login-password --region eu-central-1 | docker login --username AWS --password-stdin 552579725450.dkr.ecr.eu-central-1.amazonaws.com/aws-demo &&
docker build -t aws-demo . &&
docker tag aws-demo:latest 552579725450.dkr.ecr.eu-central-1.amazonaws.com/aws-demo:latest &&
docker push 552579725450.dkr.ecr.eu-central-1.amazonaws.com/aws-demo:latest

