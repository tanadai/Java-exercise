/*
epollを利用したイベント駆動型webサーバ

*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <fcntl.h>
#include <ctype.h>
#include <signal.h>
#include <errno.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <unistd.h>
#include <sys/stat.h>
#include <pthread.h>
#include <sys/epoll.h>

#define MAX 1024
#define SERVER_ROOT "htdocs"
#define NUM_EPOLL 10

int sockfd;
int newfd;
int epfd;

struct epoll_event ev;
struct epoll_event event[NUM_EPOLL];
struct sockaddr_in server;


void server_func(){
  while(1){
    struct sockaddr_in client;
    int newfd;
    char buf[MAX] = "";
    char method[MAX] = "";
    char url[MAX] = "";
    char protocol[MAX] = "";
    socklen_t len;
    int filefd;
    char file[MAX] = "";
    char sen[MAX]="HTTP/1.0 404 Not Found\r\n\r\n";
    int nfd;
    int i;
    
    nfd=epoll_wait(epfd, event, NUM_EPOLL, -1);
    
    for(i=0; i<nfd; i++){
      if(event[i].data.fd==sockfd){
	
	len = sizeof(client);
	if ((newfd = accept(sockfd, (struct sockaddr *) &client, &len)) < 0) {
	  perror("accept");
	  exit(EXIT_FAILURE);
	}
	memset(&event, 0, sizeof(event));
	ev.events  = EPOLLIN | EPOLLET;
	ev.data.fd = newfd;
	if (epoll_ctl(epfd, EPOLL_CTL_ADD, newfd, &event[i]) < 0) {
	  perror("epoll_ctl()");
	  exit(EXIT_FAILURE);
	}
	
	
      
	if (recv(newfd, buf, sizeof(buf), 0) < 0) {
	  perror("recv");
	  exit(EXIT_FAILURE);
	}
	
	sscanf(buf, "%s %s %s", method, url, protocol);
	printf("%s\n", buf);
	do {
	  if (strstr(buf, "\r\n\r\n"))
	    break;
	  if (strlen(buf) >= sizeof(buf))
	    memset(&buf, 0, sizeof(buf));
	} while (recv(newfd, buf+strlen(buf), sizeof(buf) - strlen(buf), 0) > 0);
	strcat(file, SERVER_ROOT);
	strcat(file, url);
	
	struct stat check;
	stat(file, &check);
	if(S_ISDIR(check.st_mode)){
	  if( file[strlen(file)-1] != '/' ) 
	    strcat(file, "/");
	  strcat(file, "index.html");
	}
	char header[MAX] = "HTTP/1.0 200 OK\r\n\r\n";
	
	if ((filefd = open(file, O_RDONLY)) < 0) {
	  send(newfd, sen, sizeof(sen), 0);
	  perror("open");
	  fprintf(stderr, "file: %s\n", file);
	}else {
	  send(newfd, header, strlen(header), 0);
	  while ((len = read(filefd, buf, sizeof(buf))) > 0) {
	    if (send(newfd, buf, len, 0) < 0) {
	      perror("send2");
	    }
	  }
	}
	close(filefd);
	close(newfd);
      }
    }
  }
}
    
int main(){
  int port =8000;
  
  
  if((epfd=epoll_create(NUM_EPOLL))<0){
    perror("socket");
    exit(EXIT_FAILURE);
  }
  
  if((sockfd=socket(PF_INET, SOCK_STREAM, 0))<0){
    perror("socket");
    exit(EXIT_FAILURE);
  }
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = INADDR_ANY;
  server.sin_port = htons(port);
  if (bind(sockfd, (struct sockaddr *) &server, sizeof(server)) < 0) {
    perror("bind");
    exit(EXIT_FAILURE);
  }
  if (listen(sockfd, 100) < 0) {
    perror("listen");
    exit(EXIT_FAILURE);
  }
  
  ev.events=EPOLLIN;
  ev.data.fd=sockfd;
  epoll_ctl(epfd, EPOLL_CTL_ADD, sockfd, &ev);
  
  
  server_func();
  
  
  close(sockfd);
  close(epfd);
  return 0;
}      
