# Tip of the Day

An application to generate a tip of the day 

# To-Do:
- Write Tests!!!!!
- Configure setup mongodb
  - Store response from ollama and save in mongodb
    - Mongo not creating the collection....
  - Render data from mongodb to ui
- Setup cron to schedule new tip


# Requirements:
   - Docker

# Steps to run locally:
1. Review Docker Compose
   - If you have a GPU uncomment lines 16-22
2. Run 'docker compose up -d'
3. Install "codegemma" model
   - Once the container is up and running you can check version utilizing [request](requests/ollama-requests.http)
   - Inside container run :
```sh 
ollama pull codegemma
```
   - This can take some time monitor the container logs until complete

