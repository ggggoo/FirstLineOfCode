# git常用命令
**[参考文档](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)**

**[官方文档](https://git-scm.com/docs)**

![图片](https://image-1254288151.cos.ap-beijing.myqcloud.com/QQ%E6%88%AA%E5%9B%BE20180321164647.png)

## git用语
```
repository  仓库，版本库。Git将管理里面的所有文件。
```

## 查看
```
git status  查看哪些文件被修改、提交
git diff <文件名.后缀>   查看修改内容（工作区和暂存区） 
git diff --cached <文件名.后缀>  查看修改内容（暂存区和分支） 
git log --pretty=oneline  查看日志（简要）
git log --graph  分支合并图
cat <文件名.后缀>  查看文件内容
git reflog   查看历史命令
```

## 撤销
```
git reset <commit.id>  版本回退。HEAD表示当前版本，HEAD^前一个版本，依此类推；
                        HEAD~100表示前100个版本。
git revert <commit.id> 版本回退。不会新增一个提交
git reset HEAD <文件名.后缀>   把暂存区的修改撤销掉，重新放回工作区                   
git checkout -- <文件名.后缀>  撤销工作区的文件修改
```

## 增删改
```
git add <文件名.后缀> 
git rm <文件名.后缀>
git commit -a -m "msg"
git remote add origin git@github.com:*****/***.git  本地仓库与远程库关联
git push -u origin master  本地master分支推送到origin（默认）
git clone git@github.com:****/***.git
```

## 密钥管理
```
ssh-keygen -t rsa -b 4096 -C "your email" 生成密钥(文件名和密码默认）
ssh -T git@github.com 测试链接
```
 [关于SHH的文档](https://help.github.com/articles/connecting-to-github-with-ssh/)

## 分支
```
git branch 查看分支
git branch <name> 创建分支
git checkout <name> 切换分支
git checkout -b <name>  创建并切换到分支
git merge <name>  先切换到master分支，再合并name分支
git merge --no-ff <name> 合并后可以从log看到分支历史
git branch -d <name> 删除分支
git branch -D <name> 强行删除
git stash   暂存工作去内容
git stash list  查看stash内容，必须在stash的分支操作
git stash pop   切换到之前的分支，恢复工作区内容并删除stash
git stash apply  恢复工作区内容，但不删除
git stash drop   删除stash
```

## 附录
![git分支1](https://image-1254288151.cos.ap-beijing.myqcloud.com/imagepath/git分支1.png)
![git分支2](https://image-1254288151.cos.ap-beijing.myqcloud.com/imagepath/git分支2.png)
![git分支4](https://image-1254288151.cos.ap-beijing.``myqcloud.com/imagepath/git分支4.png)
![git分支5](https://image-1254288151.cos.ap-beijing.myqcloud.com/imagepath/git分支5.png)

## 删除remote的文件（同时保留本地的）
```
git rm -r --cached .idea  --cached不会把本地的.idea删除
git commit -m 'delete .idea dir'
git push -u origin master
```

## rebase和merge区别
[参考文档](http://yanhaijing.com/git/2017/07/14/four-method-for-git-merge/)