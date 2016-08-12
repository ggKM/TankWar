//说明文件
void shuoming()
{
cout<<"*******************************************************************************"<<endl;
cout<<"*各项可以用平时书写习惯输入,若有未知数,必须用x表示,且各项之间必须用空格分隔,支*"<<endl;
cout<<"*持:系数是小数,指数是负数的情况,但指数不能是小数,功能选择项:                  *"<<endl;
cout<<"*            1:加法;2:减法;3:乘法;4:积分;5:微分;6:求值;7:原样输出多项式       *"<<endl;
cout<<"*******************************************************************************"<<endl;
cout<<"举例:3 x3 9.3x-3 -x....."<<endl;
}

//求幂1e-k的值
float fumi(int k)
 {
	 float a=1.0;
	 for(int i=1;i<=k;i++)
	   a=a*1e-1;
	 return a;
 }

//赋值，参数是指向头结点的指针
void fuzhi(node *&toup)
{
	float fumi(int k);
	void init(node *&toup);
	if(!toup)       init(toup);
    if(toup->next)  init(toup);//保证赋值前链表有且仅有一个头结点
//初始化各变量
char c,ch=' ';//c接受字符，ch记录上一字符，用于检错

int k2=0;//k2标志着+-号前面是否有数值,0时没有,1为有

fuhaosign k1=1;//k1是符号位正负的标志,0为负,1为正

int k3=0;//小数点的个数

int kx=0;//标志前面是否有x

list* p=toup->next,* t;
type dat=0.0;
//输入

c=getchar();
int i=0;

while(c!='\n')
{
	if(!p)
	{  p=(list*)malloc(sizeof(list)); if(!p)  {cout<<"溢出错误"<<endl;exit(0);}
       if(i==0) {toup->next=p;toup->length++;i++;p->next=NULL;t=p;}
       else
	   { t->next=p;t=p;p->next=NULL;toup->length++;}
	}
if((ch=='+'||ch=='-')&&(c=='+'||c=='-')){cout<<"未按说明输入,请下次输入前仔细阅读"<<endl;exit(-1);}//检验两次输入是否都是运算符号，若是则退出	
ch=c;
	switch(c)
{case '-':/*if(k2==1)
	{if(kx==1) {p->zhi=k1*(int)dat;dat=0.0;p=p->next;}else {p->data=k1*dat;p->zhi=0;dat=0.0;p=p->next;}}
	else if(1==kx) {p->zhi=1;p=p->next;}*/
	k1=-1;
	k2=0;
	k3=0;

	break;
 case '+':/*if(k2==1)
	{if(kx==1) {p->zhi=k1*(int)dat;dat=0.0;p=p->next;}else {p->data=k1*dat;p->zhi=0;dat=0.0;p=p->next;}}
	 else if(1==kx) {p->zhi=1;p=p->next;}*/
	k1=1;
	k2=0;
	k3=0;
	break;
 case 'x':if(k2==0)
			  p->data=k1*1;
	      else
		      p->data=k1*dat;
	dat=0.0;
	k1=1;
	k2=0;
	k3=0;
	kx=1;
	break;
 case '.':k3=1;break;
 case ' ':if(k2==1)
		  {if(kx==1) {p->zhi=k1*(int)dat;dat=0.0;p=p->next;}else {p->data=k1*dat;p->zhi=0;dat=0.0;p=p->next;}}
	      else if(1==kx) {p->zhi=1;p=p->next;}
	k1=1;
	k2=0;
	k3=0;
	kx=0;
	break;
 default:if(!k3)   dat=c-48+10*dat;//k3=0，即前面没有小数点时
	     else     {dat=dat+(c-48)*fumi(k3);k3++;}
		 k2=1;
}//  switch
c=getchar();
}//  while
if(ch=='+'||ch=='-'||ch=='.') {cout<<"输入的多项式不完整，请重新输入";exit(-1);}
if(0==kx)                     {p->data =k1*dat;p->zhi=0;}
else                          {if(k2==0) p->zhi=k1*1;else p->zhi=k1*(int)dat;}
}

//初始化,生成单链表表头，参数是指向头结点的指针
void init(node *&toup)
{
	status destory(node *&toup);
    if(toup) destory(toup);//假如有头结点或元素节点，销毁结点
    toup=(node*)malloc(sizeof(node));
    if(!toup){cout<<"溢出错误"<<endl;exit(0);}
    toup->length=0;
    toup->next=NULL;
}
//销毁全部，包括头结点
status destory(node *&toup)
{
	if(toup)
	{
		list* p=toup->next;list* t;
        while(p)
		{  
			t=p->next;
            free(p);
            p=t;
		}//while
    free(toup);
	toup=NULL;
	}//if
    return OK;
}

//找到指数与m相等的元素，返回前一元素的指针，参数是元素指针
status equalmi(list *&element,int m,list *&prep)
{
	prep=element;
    list *t=element->next;
	while(t&&t->zhi!=m)
	{
		prep=t;
		t=t->next;
	}//while
    if(t) return 1;//找到了
    else  return 0;
}

//将toup2原样赋给toup1
void copy(node*& toup1,node*& toup2)
{
	if(!toup2) {cout<<"初始化未成功"<<endl;exit(0);}
	init(toup1);
	list* t2=toup2->next,*t1=toup1->next ;
	while(t2)
	{
		list* temp=(list*)malloc(sizeof(list));
		if(!t1)
		{
			toup1->next =temp;toup1->length++;temp->data =t2->data ;temp->zhi =t2->zhi ;t2=t2->next ;t1=temp;
		}
		else
		{
			t1->next =temp;toup1->length++;temp->data =t2->data ;temp->zhi =t2->zhi ;t2=t2->next ;t1=temp;
		}
	}
	if(t1) t1->next=NULL;
}



//按指数绝对值从小到大存放，交换元素值法
void paixu(node *&toup)
{
	if(!toup)   {cout<<"初始化失败"<<endl;exit(0);}
    list *a=toup->next;
    int i=1;

    while(i<toup->length)
	{
		list* temp=a;
        list* p=a;
        list* t=p->next;
           while(t)
		   {
			   if(abs(p->zhi)>abs(t->zhi))
			   {p=t;t=t->next;}
               else
               t=t->next;
		   }//while
        a=a->next;
        if(p!=temp)  {float temp1=temp->data;int temp2=temp->zhi;temp->data=p->data;temp->zhi=p->zhi;p->data=temp1;p->zhi=temp2;}
        i++;
	}//while
}


//把指数相等的项合并,也可实现多项式加法
void neat(node *&toup)
{
	
    paixu(toup);//先排序
    list* p=toup->next;
    list* pre;
    while(p) 
	{   
		if(equalmi(p,p->zhi,pre))
		{
			p->data=pre->next->data+p->data;
            list *temp=pre->next;
            pre->next=pre->next->next;
            free(temp);
            toup->length--;
		}//if
        else  p=p->next;
	}//while，合并幂相等的项
    p=toup->next;
	list* pr=NULL;
    list* tem=NULL;
	while(p)
	{
		if(p->data ==0)
		{
			if(!pr)
			{
				tem=p;
				toup->next =p->next ;
				p=p->next ;
				toup->length--;
			    free(tem);
				tem=NULL;
			}
			else
			{
				tem=p;
				pr->next=p->next ;
				toup->length --;
				p=p->next;
				free(tem);
				tem=NULL;

			}
			

		}//if
		else
		{
			pr=p;
			p=p->next ;

		}//else
	}//while，将数据为0的项删除
	pr=NULL;
}

//得到第i个元素的指数
/*status get(node *&toup,int i)
{if(!toup)                 {cout<<"错误:没有头结点"<<endl;exit(-1);}
if(i<=0||i>(int)toup->data){cout<<"错误:i<=0或i大于元素个数"<<endl;exit(-1);}
list *t=toup;
for(;i>0;i--)
   t=t->next;
return (t->zhi);
}*/

//把两个多项式连接起来，用newtoup返回
void concat(node*& atoup,node*& btoup,node*& newtoup)
{
	if(!atoup||!btoup)  {cout<<"链初始化失败";exit(0);}
    void init(node *&toup);
	init(newtoup);
	list* ap=atoup->next;list* bp=btoup->next;list* temp=NULL;
	int i=1;
	while(ap)
	{   list* t=(list*)malloc(sizeof(list));
		if(i==1)  {newtoup->next=t;newtoup->length++;t->data=ap->data;t->zhi=ap->zhi;ap=ap->next;temp=t;i++;}
		else      {temp->next=t;newtoup->length++;t->data=ap->data;t->zhi=ap->zhi;temp=t;ap=ap->next;}
	}
    while(bp)
	{   list* t=(list*)malloc(sizeof(list));
	    if(temp) temp->next=t;
		else     temp=t;
		newtoup->length++;
		t->data=bp->data;
		t->zhi=bp->zhi;
		bp=bp->next;
		temp=t;
	}
	if(temp) temp->next=NULL;

}

//链表toup中每个节点的数据域，正变负，负变正,赋给newtoup,toup中的数据不变
void qufan(node*& toup,node*& newtoup)
{
	if(!toup) {cout<<"未初始化，请重新运行。"<<endl;exit(-1);}
	init(newtoup);
	list* t=toup->next ;
	list* newt=NULL;
	while(t)
	{
		list* temp=(list*)malloc(sizeof(list));
		if(!newt)
		{
			newtoup->next =temp;
			newtoup->length++;
			temp->data =t->data *(-1);
			temp->zhi=t->zhi;
			newt=temp;
		}
		else
		{
			newt->next =temp;
			newtoup->length ++;
			temp->data =t->data *(-1);
			temp->zhi=t->zhi;
			newt=temp;
		}
		t=t->next ;
	}//while
	if(newt) newt->next =NULL;
}

//输入x值，计算多项式的值
void qiuzhi(node *&toup,node*& resulttoup)
{
	init(toup);
    init(resulttoup);
    cout<<"输入各项:"<<endl;
	cout<<"y="<<endl;
    fuzhi(toup);
	paixu(toup);//先排序
    list* p=toup->next;
    int x0;
	cout<<"请输入x的值:"<<endl;
	cin>>x0;
    float sum=0.0,x1;
    int x=1,i=1;
      while(p)
	  {
		  for(;i<=abs(p->zhi);i++)
           x=x*x0;
          if(p->zhi>0)  x1=x;
          else
               if(p->zhi==0) x1=1;
          else x1=(float)1/x;
          sum=sum+(p->data)*x1;
          p=p->next;
	  }//while
	  list* temp=(list*)malloc(sizeof(list));
	  resulttoup->next=temp;
	  resulttoup->length++;
	  temp->data=sum;
	  temp->zhi =0;
	  temp->next =NULL;
	  neat(resulttoup);
     
}

//两个多项式相加，toup1+toup2,resulttoup返回
void jia(node *&toup1,node*& toup2,node*& resulttoup)
{
	init(toup1);
	init(toup2);
    cout<<"输入各项:"<<endl;
	cout<<"y1="<<endl;
	fuzhi(toup1);
    cout<<"y2="<<endl;
	fuzhi(toup2);
	concat(toup1,toup2,resulttoup);
	neat(resulttoup);
}

//两个多项式相减，toup1-toup2,resulttoup返回
void jian(node *&toup1,node*& toup2,node*& resulttoup)
{
    init(toup1);
	init(toup2);
    cout<<"输入各项:"<<endl;
	cout<<"y1="<<endl;
	fuzhi(toup1);
    cout<<"y2="<<endl;
	fuzhi(toup2);
	node* fantoup=NULL;
	qufan(toup2,fantoup);
	concat(toup1,fantoup,resulttoup);
	neat(resulttoup);
}

//两个多项式相乘
void cheng(node*& atoup,node*& btoup,node*& resulttoup)
{
	init(atoup);
	init(btoup);
	init(resulttoup);
    cout<<"输入各项:"<<endl;
	cout<<"y1="<<endl;
	fuzhi(atoup);
    cout<<"y2="<<endl;
	fuzhi(btoup);
	list* a=atoup->next ,*t=NULL;
    list* b=btoup->next;
	if(!a)
		copy(resulttoup,btoup);
	else if(!b)
		copy(resulttoup,atoup);
	else
	{	while(a)
		{
		    b=btoup->next;
			while(b)
			{
				list* temp=(list*)malloc(sizeof(list));
				if(!t)
				{
					t=temp;resulttoup->next =temp;
					resulttoup->length ++;
				}
				else
				{
					t->next =temp;
					resulttoup->length ++;
					t=temp;
				}
				t->data =a->data *b->data ;
				t->zhi =a->zhi +b->zhi ;
				b=b->next ;
			}//while(b)
			a=a->next ;
		}//while(a)
	    t->next =NULL;
	}//else
	neat(resulttoup);

}
//求积分
void jifen(node*& toup,node*& resulttoup)
{
	init(toup);
    init(resulttoup);
    cout<<"输入各项:"<<endl;
	cout<<"y="<<endl;
    fuzhi(toup);
	list* p=toup->next ,*t=NULL;
	while(p)
	{
		list* temp=(list*)malloc(sizeof(list));
		if(!resulttoup->next )
		{
			resulttoup->next =temp;
			resulttoup->length ++;
			t=temp;t->next =NULL;
		}
		else
		{
			t->next =temp;
			resulttoup->length  ++;
			t=temp;t->next =NULL;
		}
		if(p->zhi ==-1)
		{
			t->zhi =p->zhi;
		    t->data =p->data;
		}
		else
		{
			t->zhi =p->zhi +1;
		    t->data =p->data /t->zhi;
		}
		p=p->next ;
		
	}//while
	neat(resulttoup);
	
}
//微分
void weifen(node*& toup,node*& resulttoup)
{
	init(toup);
    init(resulttoup);
    cout<<"输入各项:"<<endl;
	cout<<"y="<<endl;
    fuzhi(toup);
	list* t=toup->next,* p=NULL;
	while(t)
	{
		list* temp=(list*)malloc(sizeof(list));
		if(!resulttoup->next )
		{
			resulttoup->next =temp;
			resulttoup->length ++;
			p=temp;p->next =NULL;
		}
		else
		{
			p->next =temp;
			resulttoup->length  ++;
			p=temp;p->next =NULL;
		}
		p->data =t->data *(t->zhi );
		p->zhi =t->zhi -1;
		t=t->next ;
	}
	neat(resulttoup);
}

//按指数从大到小存放
status outxu(node*& toup,node*& newtoup)
{
	if(!toup)   {cout<<"初始化失败"<<endl;exit(0);}
    init(newtoup);
    if(!toup->next) return 1;
	copy(newtoup,toup);
    list *p=newtoup->next;
    while(p->next )
	{
		
        list* t=p;
        list* hou=p->next;
           while(hou)
		   {
			   if(t->zhi<hou->zhi)
			     t=hou;
               hou=hou->next;
		   }//while
        list* temp=(list*)malloc(sizeof(list));
		
		if(t!=p)
		{
			float temp1=t->data;int temp2=t->zhi;
			t->data=p->data;t->zhi=p->zhi;
			p->data=temp1;p->zhi=temp2;
		}
		
        p=p->next;
        
	}//while
	return 1;
}

//输出多项式
status out(node*& toup)
{
	extern yunsuan;
	if(!toup) {cout<<"要输出的链表未初始化,请重新运行"<<endl;exit(-1);}
	
    node* outresult=NULL;
	outxu(toup,outresult);
	cout<<"结果是:"<<endl;
	list* temp=outresult->next;
	int i=1;
    cout<<setprecision(3);
	if(!temp)
	{
		if(yunsuan==4)
			cout<<"任意常数"<<endl;
		else
			cout<<0<<endl;
            
		return 1;
		
	}
	while(temp)
	{
		switch(temp->zhi)
		{
		case 0:if(1==i)
			   {
				   cout<<temp->data;i++;
			   }//if
			   else
			   {
				   if(temp->data>=0)
					    cout<<'+'<<temp->data;
				   else cout<<temp->data ;
			   }//else
			   break;
		case 1:if(1==i)
			   {
				   if(temp->data>0)
				   {if(1==temp->data )
						   cout<<'x';
	                else   cout<<temp->data <<'x';
				   }
				   else
				   {
					   if(-1==temp->data )
						    cout<<"-x";
					   else cout<<temp->data <<'x';
				   }
				   i++;
			   }//if
			   else
			   {
				   if(temp->data >0)
				   {
					   if(1==temp->data)
						    cout<<"+x";
					   else cout<<'+'<<temp->data <<'x';
				   }
				   else
				   {
					   if(-1==temp->data )
						    cout<<"-x";
					   else cout<<temp->data <<'x';
				   }
			   }//else
			   break;
		default:if(1==i)
				{
					if(temp->data >0)
					{
						if(1==temp->data )
							 cout<<"x^"<<temp->zhi ;
						else cout<<temp->data <<"x^"<<temp->zhi ;
					}
					else
					{
						if(-1==temp->data )
							 cout<<"-x^"<<temp->zhi ;
						else cout<<temp->data <<"x^"<<temp->zhi ;
					}
					i++;
				}//if
			    else
				{
					if(temp->data >0)
					{
						if(1==temp->data )
							 cout<<"+x^"<<temp->zhi ;
						else cout<<'+'<<temp->data <<"x^"<<temp->zhi;
					}
					else
					{
						if(-1==temp->data )
							 cout<<"-x^"<<temp->zhi ;
						else cout<<temp->data <<"x^"<<temp->zhi ;
					}
				}//else
			
		}//switch
		temp=temp->next ;
	}//while
    if(yunsuan==4)
	    cout<<"+任意常数";
    cout<<endl;
    free(outresult);
    outresult=NULL;
    return 1;
}

