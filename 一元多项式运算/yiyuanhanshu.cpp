//˵���ļ�
void shuoming()
{
cout<<"*******************************************************************************"<<endl;
cout<<"*���������ƽʱ��дϰ������,����δ֪��,������x��ʾ,�Ҹ���֮������ÿո�ָ�,֧*"<<endl;
cout<<"*��:ϵ����С��,ָ���Ǹ��������,��ָ��������С��,����ѡ����:                  *"<<endl;
cout<<"*            1:�ӷ�;2:����;3:�˷�;4:����;5:΢��;6:��ֵ;7:ԭ���������ʽ       *"<<endl;
cout<<"*******************************************************************************"<<endl;
cout<<"����:3 x3 9.3x-3 -x....."<<endl;
}

//����1e-k��ֵ
float fumi(int k)
 {
	 float a=1.0;
	 for(int i=1;i<=k;i++)
	   a=a*1e-1;
	 return a;
 }

//��ֵ��������ָ��ͷ����ָ��
void fuzhi(node *&toup)
{
	float fumi(int k);
	void init(node *&toup);
	if(!toup)       init(toup);
    if(toup->next)  init(toup);//��֤��ֵǰ�������ҽ���һ��ͷ���
//��ʼ��������
char c,ch=' ';//c�����ַ���ch��¼��һ�ַ������ڼ��

int k2=0;//k2��־��+-��ǰ���Ƿ�����ֵ,0ʱû��,1Ϊ��

fuhaosign k1=1;//k1�Ƿ���λ�����ı�־,0Ϊ��,1Ϊ��

int k3=0;//С����ĸ���

int kx=0;//��־ǰ���Ƿ���x

list* p=toup->next,* t;
type dat=0.0;
//����

c=getchar();
int i=0;

while(c!='\n')
{
	if(!p)
	{  p=(list*)malloc(sizeof(list)); if(!p)  {cout<<"�������"<<endl;exit(0);}
       if(i==0) {toup->next=p;toup->length++;i++;p->next=NULL;t=p;}
       else
	   { t->next=p;t=p;p->next=NULL;toup->length++;}
	}
if((ch=='+'||ch=='-')&&(c=='+'||c=='-')){cout<<"δ��˵������,���´�����ǰ��ϸ�Ķ�"<<endl;exit(-1);}//�������������Ƿ���������ţ��������˳�	
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
 default:if(!k3)   dat=c-48+10*dat;//k3=0����ǰ��û��С����ʱ
	     else     {dat=dat+(c-48)*fumi(k3);k3++;}
		 k2=1;
}//  switch
c=getchar();
}//  while
if(ch=='+'||ch=='-'||ch=='.') {cout<<"����Ķ���ʽ������������������";exit(-1);}
if(0==kx)                     {p->data =k1*dat;p->zhi=0;}
else                          {if(k2==0) p->zhi=k1*1;else p->zhi=k1*(int)dat;}
}

//��ʼ��,���ɵ������ͷ��������ָ��ͷ����ָ��
void init(node *&toup)
{
	status destory(node *&toup);
    if(toup) destory(toup);//������ͷ����Ԫ�ؽڵ㣬���ٽ��
    toup=(node*)malloc(sizeof(node));
    if(!toup){cout<<"�������"<<endl;exit(0);}
    toup->length=0;
    toup->next=NULL;
}
//����ȫ��������ͷ���
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

//�ҵ�ָ����m��ȵ�Ԫ�أ�����ǰһԪ�ص�ָ�룬������Ԫ��ָ��
status equalmi(list *&element,int m,list *&prep)
{
	prep=element;
    list *t=element->next;
	while(t&&t->zhi!=m)
	{
		prep=t;
		t=t->next;
	}//while
    if(t) return 1;//�ҵ���
    else  return 0;
}

//��toup2ԭ������toup1
void copy(node*& toup1,node*& toup2)
{
	if(!toup2) {cout<<"��ʼ��δ�ɹ�"<<endl;exit(0);}
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



//��ָ������ֵ��С�����ţ�����Ԫ��ֵ��
void paixu(node *&toup)
{
	if(!toup)   {cout<<"��ʼ��ʧ��"<<endl;exit(0);}
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


//��ָ����ȵ���ϲ�,Ҳ��ʵ�ֶ���ʽ�ӷ�
void neat(node *&toup)
{
	
    paixu(toup);//������
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
	}//while���ϲ�����ȵ���
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
	}//while��������Ϊ0����ɾ��
	pr=NULL;
}

//�õ���i��Ԫ�ص�ָ��
/*status get(node *&toup,int i)
{if(!toup)                 {cout<<"����:û��ͷ���"<<endl;exit(-1);}
if(i<=0||i>(int)toup->data){cout<<"����:i<=0��i����Ԫ�ظ���"<<endl;exit(-1);}
list *t=toup;
for(;i>0;i--)
   t=t->next;
return (t->zhi);
}*/

//����������ʽ������������newtoup����
void concat(node*& atoup,node*& btoup,node*& newtoup)
{
	if(!atoup||!btoup)  {cout<<"����ʼ��ʧ��";exit(0);}
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

//����toup��ÿ���ڵ�����������为��������,����newtoup,toup�е����ݲ���
void qufan(node*& toup,node*& newtoup)
{
	if(!toup) {cout<<"δ��ʼ�������������С�"<<endl;exit(-1);}
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

//����xֵ���������ʽ��ֵ
void qiuzhi(node *&toup,node*& resulttoup)
{
	init(toup);
    init(resulttoup);
    cout<<"�������:"<<endl;
	cout<<"y="<<endl;
    fuzhi(toup);
	paixu(toup);//������
    list* p=toup->next;
    int x0;
	cout<<"������x��ֵ:"<<endl;
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

//��������ʽ��ӣ�toup1+toup2,resulttoup����
void jia(node *&toup1,node*& toup2,node*& resulttoup)
{
	init(toup1);
	init(toup2);
    cout<<"�������:"<<endl;
	cout<<"y1="<<endl;
	fuzhi(toup1);
    cout<<"y2="<<endl;
	fuzhi(toup2);
	concat(toup1,toup2,resulttoup);
	neat(resulttoup);
}

//��������ʽ�����toup1-toup2,resulttoup����
void jian(node *&toup1,node*& toup2,node*& resulttoup)
{
    init(toup1);
	init(toup2);
    cout<<"�������:"<<endl;
	cout<<"y1="<<endl;
	fuzhi(toup1);
    cout<<"y2="<<endl;
	fuzhi(toup2);
	node* fantoup=NULL;
	qufan(toup2,fantoup);
	concat(toup1,fantoup,resulttoup);
	neat(resulttoup);
}

//��������ʽ���
void cheng(node*& atoup,node*& btoup,node*& resulttoup)
{
	init(atoup);
	init(btoup);
	init(resulttoup);
    cout<<"�������:"<<endl;
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
//�����
void jifen(node*& toup,node*& resulttoup)
{
	init(toup);
    init(resulttoup);
    cout<<"�������:"<<endl;
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
//΢��
void weifen(node*& toup,node*& resulttoup)
{
	init(toup);
    init(resulttoup);
    cout<<"�������:"<<endl;
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

//��ָ���Ӵ�С���
status outxu(node*& toup,node*& newtoup)
{
	if(!toup)   {cout<<"��ʼ��ʧ��"<<endl;exit(0);}
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

//�������ʽ
status out(node*& toup)
{
	extern yunsuan;
	if(!toup) {cout<<"Ҫ���������δ��ʼ��,����������"<<endl;exit(-1);}
	
    node* outresult=NULL;
	outxu(toup,outresult);
	cout<<"�����:"<<endl;
	list* temp=outresult->next;
	int i=1;
    cout<<setprecision(3);
	if(!temp)
	{
		if(yunsuan==4)
			cout<<"���ⳣ��"<<endl;
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
	    cout<<"+���ⳣ��";
    cout<<endl;
    free(outresult);
    outresult=NULL;
    return 1;
}

