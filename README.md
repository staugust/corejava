# core java code

# Build
mvn clean package 

# RUN

docker rm -f corejava || true && docker run -d -p 8080:8080 -p 4848:4848 --name corejava augusto.cn/corejava 
