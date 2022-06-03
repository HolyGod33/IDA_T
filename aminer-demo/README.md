# aminer-demo

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### 重要事项
执行```npm run serve```后会显示running在/ida/static下，但实际上在/ida/aps下，请手
动修改地址栏url

### how to deploy to IDA?
执行```npm run build```打包生成dist，将lib、aps_assets文件夹复制到static目录下，
将index.html复制到templates目录下，并改名为aps.html，并把文件中的```<html lang="">```修改为
```<html lang="en" xmlns:th="http://www.thymeleaf.org">```即可

### 如果需要修改二级url（/aps)或静态资源目录
静态资源目录请修改vue.config.js中的assetsDir，二级url请修改router目录下的index.js
中的base，同时后端需要同步修改

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
