# Tip of the Day
A Sring Web application that leverages Ollama to generate a tip for three different topics for each day.

![demo.png](images/demo.png)

# Purpose:
The purpose of this project is to apply the knowledge and skills i have acquired while learning Java. Through this project, I aim to implement various concepts learned and solidify my understanding of these topics. 

# Setup:
## Steps to setup locally:
1. Utilizing docker or podman 
   1. Review docker-compose.yml file
2. Run compose up
```shell
# Utilizing Podman
podman compose up -d  
# Utilizing Docker
docker compose up -d
```
3. Once Containers are up and running we will need to exec commands within the ollama container
   - List all running containers
```shell 
# Podman list running containers
podman ps

# Podman list running containers
docker ps
```
   - Get the first 3-5 characters of the container id and we will enter into the container terminal
```shell
# Podamn 
podman exec -it xxxx /bin/bash
# Docker
docker exec -it xxxx /bin/bash
```
   - Install "codegemma" model in container
```sh 
ollama pull codegemma
```
Once complete you can exit the terminal

![terminal.png](images/terminal.png)

# How to Use:

At this time the API endpoint to store the data in mongo is triggered to run automatically starting at midnight. For
initial startup of the app you will need to hit the endpoint. Due to the processing required to generate a response it
is best to run each endpoint one at time until the response has generated.
Once you have data retrieved and stored in mongo the response can be retrieved and viewed on the home page.

| Endpoint                                                   | Scheduled |
|------------------------------------------------------------|-----------|
| [Home](http://localhost:8080/)                             | N/A       |
| [Java Tip Endpoint](http:\\localhost:8080\ollama\java)     | 12:01 AM  |
| [Python Tip Endpoint](http:\\localhost:8080\ollama\python) | 12:05 AM  |
| [Design Tip Endpoint](http:\\localhost:8080\ollama\design) | 12:10 AM  |


# To-Do:

- [ ] Write Tests!
- [ ] Getting Daily Tips
    - [x] Setup cron to schedule new tip
    - [ ] On Startup if no tip of the Day, Get new Tip
- [ ] Refine Visual UI
- [ ] Containerize Application



