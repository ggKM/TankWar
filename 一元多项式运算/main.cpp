#include"yiyuantou.h"
#include"yiyuanjiegou.cpp"
#include"yiyuanhanshu.cpp"
int yunsuan=0;
int main()
{

	system("color f0");
	shuoming();
    
    short cuo=0;
    node *tou1=NULL,*tou2=NULL,* result=NULL;
    while(1)
	{  
		do
		{
			cout<<"请选择功能:"<<endl;
			cin>>yunsuan;
			if(!cin)
			{
				cout<<"您输入的不是数字,请重新运行程序"<<endl;
			    exit(0);
			}//if
			switch(yunsuan)
			{
			     case 1:cuo=0;jia(tou1,tou2,result);break;
		         case 2:cuo=0;jian(tou1,tou2,result);break;
	    	     case 3:cuo=0;cheng(tou1,tou2,result);break;
	             case 4:cuo=0;jifen(tou1,result);break;
			     case 5:cuo=0;weifen(tou1,result);break;
		         case 6:cuo=0;qiuzhi(tou1,result);break;
                 case 7:cuo=0;init(tou1);cout<<"输入各项:"<<endl;cout<<"y="<<endl;fuzhi(tou1);outxu(tou1,result);break;
				 default:cuo=1;cout<<"超出功能选择范围,请重新选择:"<<endl;
			}//switch
			
		} while(cuo==1);//do
        
		out(result);
		destory(tou1);destory(tou2);destory(result);
	
		short i=0;
		cout<<"是否清屏(1:是)(0:否):";
		cin>>i;
		if(i==1) {system("cls");shuoming();}
		else     cout<<"*********************"<<endl;
	    
	}//while
    return 0;
}