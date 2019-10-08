#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char** argv){
  int fd;
  while(argc > 1){
    fd = open(argv[argc-1], O_RDONLY);
    argc--;
    if(fd < 0){
      printf("%sが見つかりません。\n",argv[argc]);
      continue;
    }
    char buf[4096];
    while(1){
      int count = read(fd, buf, 4096);
      if(count <= 0) break;
      write(1, buf, count);
    }
    close(fd);
  }
}
