#### weight计算
```
weight计算原理：控件的原有宽度 + 剩余空间所占百分比的宽度
如果两个控件的width是match_parent，则一个控件的宽度：
L（当前Button1的宽度为match_parent）+（ L(屏幕宽度) - 2L(两个Button的宽度)）* 1/3 = 2/3L；
如果是0dp，则一个控件的宽度：
0 + （L - 0）* 1/3 = 1/3L
```