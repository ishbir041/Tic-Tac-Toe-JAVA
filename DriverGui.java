//*********************************************************
//** 		Author Details							*******
//**		Ishbir Walia 2015041					*******
//*********************************************************


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class DriverGui {

private	JFrame mainframe;
private	JPanel left,right,gpan;

private	JLabel out;
private	int mode,fir;    // where 1=user v user , 2=user v cpu , 3=cpu v ai, 4=user v ai
private	String user1,user2;
private	String u1,u2;
private	int turn;    // 1 for user1 and 2 for user2
private	int x,y;  // button pressed coordinates
private	Board b;
private	Timer t;
private JButton curr;


	DriverGui()
	{
		mode=-1;
		turn=-1;
		user1=new String();
		user2=new String();
		u1=new String("X");
		u2=new String("O");
		x=-1;
		y=-1;
		fir=0;
		curr=null;
		 t=new Timer(1000,new ActionListener()            //for CPU VS AI Bot
			{
				public void actionPerformed(ActionEvent e)
				{
					if(b.checkState()==0 && mode==3)
					{
						if(turn==1)
						{
							out.setText(user2+"'s turn");
							play();
							turn=2;
						}
						else if(turn==2)
						{
							out.setText(user1+"'s turn");
							play();
							turn=1;
						}
					}

				}
			});


		mainframe=new JFrame();
		//mainframe.setLayout(new GridLayout(1,1,0,10));
		mainframe.setSize(900, 900);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.getContentPane().setBackground(Color.cyan);

		mainmenu();

		//mainframe.pack();
		mainframe.setVisible(true);
	}


	public void mainmenu()
	{
		JPanel pan=new JPanel();
		pan.setLayout(new GridLayout(3,1,0,40));

		JLabel intro=new JLabel();
		intro.setFont(new Font("TIC-TAC-TOE",Font.ITALIC,40));
		intro.setHorizontalAlignment(JLabel.CENTER);
		intro.setText("TIC-TAC-TOE");

		JButton start=new JButton("Start");
		start.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				//start.setBackground(Color.red);  //test
				secondMenu();
				//grid();
			}
		});

		start.setOpaque(true);
		//start.setPreferredSize(new Dimension(30,40));


		JButton exit=new JButton("Exit");
		exit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		exit.setOpaque(false);

		pan.setBackground(Color.CYAN);
		pan.add(intro);
		pan.add(start);
		pan.add(exit);

		pan.setVisible(true);
		left=new JPanel();
		left.setLayout(new GridLayout(1,1));
		left.add(pan);

		mainframe.getContentPane().add(left);

	}

	public void secondMenu()
	{

		JButton uvu,uvc,cva,uva;

		uvu=new JButton("User1 VS User2");
		uvc=new JButton("User VS CPU");
		cva=new JButton("CPU VS AI Bot");
		uva=new JButton("User VS AI Bot");

		uvu.setBackground(Color.LIGHT_GRAY);
		uvu.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//add stuff
						if (mode==-1)
						{
							JFrame tmp=new JFrame("Input's");
							tmp.setSize(300,400);
							tmp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

							JLabel f,s;
							f=new JLabel("User 1");
							s=new JLabel("User 2");
							JTextArea ff,ss;
							ff=new JTextArea("");
							ss=new JTextArea("");
							JLabel t=new JLabel("Wrong Input");
							t.setForeground(Color.red);
							t.setVisible(false);



							tmp.setLayout(new GridLayout(3,2,20,20));
							tmp.getContentPane().add(f);
							tmp.getContentPane().add(ff);
							tmp.getContentPane().add(s);
							tmp.getContentPane().add(ss);
							tmp.getContentPane().add(t);



							JButton ok=new JButton("OK");
							ok.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
										{
											if(!(ff.getText().equals("")) && !(ss.getText().equals("")))
											{
												reinit();
												user1=ff.getText();
												user2=ss.getText();
												uvu.setBackground(Color.orange);
												mode=1;
												curr=uvu;
												turn=1;
												b=new Board();
												//function call

												tmp.dispose();
												out.setText(user1+"'s turn");

												//play();
												//TODO fix this, will get stuck


											}
											else
											{
												tmp.getContentPane().setVisible(false);
												t.setVisible(true);
												tmp.getContentPane().setVisible(true);
												//tmp.getContentPane().remove(t);

											}

										}


									});
							tmp.getContentPane().add(ok);
							tmp.setVisible(true);

						}

					}

				});
		uvc.setBackground(Color.LIGHT_GRAY);
		uvc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//add stuff
				if (mode==-1)
				{
					JFrame tmp=new JFrame("Input's");
					tmp.setSize(300,400);
					tmp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					JLabel f;
					f=new JLabel("User 1");
					//s=new JLabel("User 2");
					JTextArea ff;
					ff=new JTextArea("");
					//ss=new JTextArea("");
					JLabel t=new JLabel("Wrong Input");
					t.setForeground(Color.red);
					t.setVisible(false);



					tmp.setLayout(new GridLayout(3,2,20,20));
					tmp.getContentPane().add(f);
					tmp.getContentPane().add(ff);
					//tmp.getContentPane().add(s);
					//tmp.getContentPane().add(ss);
					tmp.getContentPane().add(t);



					JButton ok=new JButton("OK");
					ok.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									if(!(ff.getText().equals("")) )
									{
										reinit();

										String aa,bb;
										aa=ff.getText();
										//user2=ss.getText();
										bb=new String("CPU");
										Random r=new Random();
										int i=r.nextInt(2);
										if(i==0)
										{
											user1=new String(aa);
											user2=new String(bb);
										}
										else if(i==1)
										{
											user1=new String(bb);
											user2=new String(aa);
										}
										mode=2;
										uvc.setBackground(Color.orange);
										curr=uvc;
										turn=1;
										b=new Board();
										//function call

										tmp.dispose();
										out.setText(user1+"'s turn");
										if(user1.equals("CPU"))
										{

											play();
											turn=2;
										}



									}
									else
									{
										tmp.getContentPane().setVisible(false);
										t.setVisible(true);
										tmp.getContentPane().setVisible(true);
										//tmp.getContentPane().remove(t);

									}

								}


							});
					tmp.getContentPane().add(ok);
					tmp.setVisible(true);

				}

			}

		});
		cva.setBackground(Color.LIGHT_GRAY);
		cva.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//add stuff
				if(mode==-1)
				{
					reinit();
					String aa,bb;
					aa=new String("CPU");
					//user2=ss.getText();
					bb=new String("AI Bot");
					Random r=new Random();
					int i=r.nextInt(2);
					if(i==0)
					{
						user1=new String(aa);
						user2=new String(bb);
					}
					else if(i==1)
					{
						user1=new String(bb);
						user2=new String(aa);
					}
					mode=3;
					cva.setBackground(Color.orange);
					curr=cva;
					turn=1;
					b=new Board();
					//Timer t=null;


				//	t.setRepeats(false);
					out.setText(user1+"'s turn");
					t.start();





					//function call
				}

			}

		});
		uva.setBackground(Color.LIGHT_GRAY);

		uva.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//add stuff
				if (mode==-1)
				{
					JFrame tmp=new JFrame("Input's");
					tmp.setSize(300,400);
					tmp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					JLabel f;
					f=new JLabel("User 1");
					//s=new JLabel("User 2");
					JTextArea ff;
					ff=new JTextArea("");
					//ss=new JTextArea("");
					JLabel t=new JLabel("Wrong Input");
					t.setForeground(Color.red);
					t.setVisible(false);



					tmp.setLayout(new GridLayout(3,2,20,20));
					tmp.getContentPane().add(f);
					tmp.getContentPane().add(ff);
					//tmp.getContentPane().add(s);
					//tmp.getContentPane().add(ss);
					tmp.getContentPane().add(t);



					JButton ok=new JButton("OK");
					ok.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									if(!(ff.getText().equals("")) )
									{
										reinit();
										String aa,bb;
										aa=ff.getText();
										//user2=ss.getText();
										bb=new String("AI Bot");
										Random r=new Random();
										int i=r.nextInt(2);
										if(i==0)
										{
											user1=new String(aa);
											user2=new String(bb);
										}
										else if(i==1)
										{
											user1=new String(bb);
											user2=new String(aa);
										}
										mode=4;
										uva.setBackground(Color.orange);
										curr=uva;
										turn=1;
										fir=0;
										b=new Board();


										tmp.dispose();
										out.setText(user1+"'s turn");
										if(user1.equals("AI Bot"))
										{

											play();
											turn=2;
										}



									}
									else
									{
										tmp.getContentPane().setVisible(false);
										t.setVisible(true);
										tmp.getContentPane().setVisible(true);
										//tmp.getContentPane().remove(t);

									}

								}


							});
					tmp.getContentPane().add(ok);
					tmp.setVisible(true);

				}


			}

		});
		JButton exit=new JButton("Exit");

		exit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		exit.setPreferredSize(new Dimension(400,60));


		JPanel pan=new JPanel();
		left.setVisible(false);
		mainframe.remove(left);


		left=new JPanel();
		left.setLayout(new BoxLayout(left,BoxLayout.Y_AXIS));

		pan.setLayout(new GridLayout(4,1,0,30));
	//	pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
		pan.setBackground(Color.cyan);
		pan.add(uvu);
	//	pan.add(Box.createVerticalGlue());
		pan.add(uvc);
	//	pan.add(Box.createVerticalGlue());
		pan.add(cva);
	//	pan.add(Box.createVerticalGlue());
		pan.add(uva);
	//	pan.add(Box.createVerticalGlue());
		//mainframe.setVisible(false);
		left.add(pan);
		//left.add(Box.createVerticalGlue());
		left.add(exit);
		left.setBackground(Color.cyan);
		//mainframe.remove(right);
		mainframe.getContentPane().add(left);
		mainframe.getContentPane().setBackground(Color.cyan);
		mainframe.setVisible(true);
		grid();


	}

	public void makeButton(String s,JPanel p,int i,int j)
	{
		final JButton gr=new JButton("");
		gr.setBackground(Color.white);
		gr.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//gr.setBackground(Color.red);
				if(mode!=-1 && gr.getText().equals(""))
				{
					//gr.setBackground(Color.red);
					//gr.setText("HI");
					if(turn==1) //for user1
					{
						gr.setText(u1);
					//	gr.setBackground(Color.red);
						//play();
						x=i;
						y=j;
						play();
						turn=2;
						if(mode==2 || mode==4)
						{
							play();
							turn=1;
						}
					}
					else if(turn==2) //for user2
					{
						gr.setText(u2);
					//	gr.setBackground(Color.blue);
						//play();
						x=i;
						y=j;
						play();
						turn=1;

						if(mode==2 || mode==4)
						{
							play();
							turn=2;
						}
					}


				}

			}
		});
		p.add(gr);

	}


	public void grid()
	{
		 gpan=new JPanel();
		gpan.setLayout(new GridLayout(3,3));

		int i,j;
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				makeButton(i+""+j,gpan,i,j);
			}
		}
		out=new JLabel("Output Box for showing turns and result's");
		out.setOpaque(true);
		out.setPreferredSize(new Dimension(100,100));
		mainframe.getContentPane().setLayout(new GridLayout(1,2));
		right=new JPanel();
		right.setLayout(new BoxLayout(right,BoxLayout.Y_AXIS));
	//	right.add(Box.createVerticalGlue());

		right.add(gpan);
		right.add(Box.createVerticalGlue());
		right.add(out);
		right.add(Box.createVerticalGlue());
		right.setBackground(Color.cyan);
		left.setVisible(false);
		//mainframe.setVisible(false);
		//mainframe.getContentPane().setVisible(false);
		mainframe.getContentPane().add(right);
		//mainframe.getContentPane().setVisible(true);
		left.setVisible(true);
		//reinit();
		//mainframe.setVisible(true);
	}


	public void play()
	{

		if(mode!=-1 && !(user1.equals("")) && !(user2.equals("")))
		{
			//Board b=new Board();

			if(b.checkState()==0)
			{
				if(turn==1)
				{


					if(user1.equals("CPU") &&(mode==2 || mode==3))
					{
						// TODO for cpu
						out.setText(user2+"'s turn");

						cpu(b,u1);

						//turn=1;
					}
					else if(user1.equals("AI Bot") && (mode==3 || mode==4))
					{
						//TODO for AI Bot
						out.setText(user2+"'s turn");
						if(fir==0)
						{
							cpu(b,u1);
						}
						else
						{
							ai(b,u1,u2);
						}
						//turn=1;
					}
					else
					{
						// for user
						//turn=1;
						out.setText(user2+"'s turn");
					//	while(x==-1 && y==-1);
						b.put(x, y, 'X');

					}

				}
				else if(turn==2)
				{
					//for second players turn
					if(user2.equals("CPU") &&(mode==2 || mode==3))
					{
						// TODO for cpu
						out.setText(user1+"'s turn");
						//delay();

						cpu(b,u2);
						//delay();

						//turn=2;

					}
					else if(user2.equals("AI Bot") && (mode==3 || mode==4))
					{
						//TODO for AI Bot
						out.setText(user1+"'s turn");

						ai(b,u2,u1);
						//turn=2;
					}
					else
					{
						//for user
						out.setText(user1+"'s turn");
						//turn=2;
						//while(x==-1 && y==-1);
						b.put(x, y, 'O');

					}
				}
			}
			if(mode==4)
			{
			fir++;
			}
			//b.print();
			int k=b.checkState();
			if(k==1)
			{
				//TODO user1 wins
				if(t.isRunning())
					t.stop();

				out.setText(user1+" Wins");
				disp(u1);
				mode=-1;
				curr.setBackground(Color.lightGray);
				curr=null;
				x=-1;
				y=-1;


			}
			else if(k==2)
			{
				//TODO user2 wins
				if(t.isRunning())
					t.stop();

				out.setText(user2+" Wins");
				disp(u2);
				mode=-1;
				curr.setBackground(Color.lightGray);
				curr=null;
				x=-1;
				y=-1;

			}
			else if(k==3)
			{
				//TIE
				if(t.isRunning())
					t.stop();

				out.setText("its a TIE ");
				mode=-1;
				curr.setBackground(Color.lightGray);
				curr=null;
				x=-1;
				y=-1;
			}

		}



	}
	//****************************************************
	//				BUTTONS LIGHT UP/CHANGE COLOR
	//****************************************************

	public void disp(String us)
	{
		char [][]ab=b.get();
		char win=us.charAt(0);
		//System.out.println(win);
		int i,j,c;
		c=0;
		int [][]fmove=new int[3][2];
		int k=0;

		for(i=0;i<3;i++)
		{
			c=0;
			k=0;
			fmove=new int[3][2];

			for(j=0;j<3;j++)
			{
				if(ab[i][j]==win)
				{
					c++;
					fmove[k][0]=i;
					fmove[k][1]=j;
					k++;
				}
			}
			if(c==3)
			{
				//System.out.println("X Wins ");
				buttonColor(fmove[0][0],fmove[0][1]);
				buttonColor(fmove[1][0],fmove[1][1]);
				buttonColor(fmove[2][0],fmove[2][1]);

				return ;
			}
		}

		for(i=0;i<3;i++)
		{
			c=0;
			k=0;
			fmove=new int[3][2];

			for(j=0;j<3;j++)
			{
				if(ab[j][i]==win)
				{
					c++;
					fmove[k][0]=j;
					fmove[k][1]=i;
					k++;

				}
			}
			if(c==3)
			{
				//System.out.println("X Wins ");
				buttonColor(fmove[0][0],fmove[0][1]);
				buttonColor(fmove[1][0],fmove[1][1]);
				buttonColor(fmove[2][0],fmove[2][1]);

				return ;
			}
		}

		fmove=new int[3][2];
		c=0;
		k=0;
		for(i=0;i<3;i++)
		{
			if(ab[i][i]==win)
			{
				c++;
				fmove[k][0]=i;
				fmove[k][1]=i;
				k++;

			}
		}
		if(c==3)
		{
			//System.out.println("X Wins ");
			buttonColor(fmove[0][0],fmove[0][1]);
			buttonColor(fmove[1][0],fmove[1][1]);
			buttonColor(fmove[2][0],fmove[2][1]);

			return ;
		}


		c=0;
		k=0;
		for(i=0;i<3;i++)
		{
			if(ab[i][2-i]==win)
			{
				c++;
				fmove[k][0]=i;
				fmove[k][1]=2-i;
				k++;

			}
		}
		if(c==3)
		{
			//System.out.println("X Wins ");
			buttonColor(fmove[0][0],fmove[0][1]);
			buttonColor(fmove[1][0],fmove[1][1]);
			buttonColor(fmove[2][0],fmove[2][1]);

			return ;
		}

	}

	public void buttonColor(int i,int j)
	{
		int num=i*3+j;
		int k=0;
		for(Component c :gpan.getComponents())
		{
			JButton tmp=(JButton)c;
			if(k==num)
			{
				tmp.setBackground(Color.green);

			}
			k++;
		}

	}

	//******************************************************************
	//   			Reinitialize everything after game over
	//*********************************************************************

	public void reinit()
	{
		mode=-1;
		x=-1;
		y=-1;
		user1=new String("");
		user2=new String("");

		ArrayList<JButton> bb=new ArrayList<JButton>();
		for(Component c : gpan.getComponents())
		{
			if(c instanceof JButton)
			{
				bb.add((JButton)c);
			}
		}
		//int i=0;
		for(JButton tmp:bb)
		{

			tmp.setBackground(Color.white);
			tmp.setText("");


			//i++;

		}

		//System.out.println("count="+bb.size());

	}

	//*********************************************************
	//				Button Value Assign
	//*********************************************************
	public void buttonAssign(int i,int j,String val)
	{
		int num=i*3+j;
		int k=0;
		for(Component c :gpan.getComponents())
		{
			JButton tmp=(JButton)c;
			if(k==num)
			{
				tmp.setText(val);

			}
			k++;
		}
	}


	//**********************************************************
	//				DELAY of 1 Second
	//*********************************************************

	public void delay()
	{
		try
		{
			//TimeUnit.SECONDS.sleep(1);
			Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
	}



	//*****************************************************************
	//						CPU
	//****************************************************************

	public void cpu(Board a ,String cp)
	{
		Random r=new Random();
		char cpp=cp.charAt(0);
		int m,n;
		m=r.nextInt(3);
		n=r.nextInt(3);
		int k;
		k=a.put(m,n,cpp);
		if(k==0)
		{
			while(k==0)
			{
				m=r.nextInt(3);
				n=r.nextInt(3);
				k=a.put(m, n, cpp);
			}
		}
		buttonAssign(m,n,cp);

	}


	//******************************************************************************
	//					AI Bot
	//******************************************************************************

	public void ai(Board a, String ai,String op)
	{
		char [][]ab;
		char aib,opo;
		aib=ai.charAt(0);
		opo=op.charAt(0);

		ab=a.get();
		int []fmove=new int[2];
		int sco;

		sco=minmax(ab,0,fmove,aib,opo);
		//System.out.println("NEW VALUE ");
		if(a.checkState()==0)
		  {
			a.put(fmove[0], fmove[1], aib);
			buttonAssign(fmove[0],fmove[1],ai);
		  }
	}



	int whowon(char[][]ab)
	{
		int i,j,c;
		c=0;

		for(i=0;i<3;i++)
		{
			c=0;
			for(j=0;j<3;j++)
			{
				if(ab[i][j]=='X')
				{
					c++;
				}
			}
			if(c==3)
			{
				//System.out.println("X Wins ");
				return 1;
			}
		}

		for(i=0;i<3;i++)
		{
			c=0;
			for(j=0;j<3;j++)
			{
				if(ab[j][i]=='X')
				{
					c++;
				}
			}
			if(c==3)
			{
				//System.out.println("X Wins ");
				return 1;
			}
		}

		c=0;
		for(i=0;i<3;i++)
		{
			if(ab[i][i]=='X')
				c++;
		}
		if(c==3)
		{
			//System.out.println("X Wins ");
			return 1;
		}


		c=0;
		for(i=0;i<3;i++)
		{
			if(ab[i][2-i]=='X')
				c++;
		}
		if(c==3)
		{
			//System.out.println("X Wins ");
			return 1;
		}


		//*************************
		//For O
		//************************

		for(i=0;i<3;i++)
		{
			c=0;
			for(j=0;j<3;j++)
			{
				if(ab[i][j]=='O')
				{
					c++;
				}
			}
			if(c==3)
			{
				//System.out.println("O Wins ");
				return 2;
			}
		}

		for(i=0;i<3;i++)
		{
			c=0;
			for(j=0;j<3;j++)
			{
				if(ab[j][i]=='O')
				{
					c++;
				}
			}
			if(c==3)
			{
				//System.out.println("O Wins ");
				return 2;
			}
		}

		c=0;
		for(i=0;i<3;i++)
		{
			if(ab[i][i]=='O')
				c++;
		}
		if(c==3)
		{
			//System.out.println("O Wins ");
			return 2;
		}


		c=0;
		for(i=0;i<3;i++)
		{
			if(ab[i][2-i]=='O')
				c++;
		}
		if(c==3)
		{
			//System.out.println("O Wins ");
			return 2;
		}
		c=0;

		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				if(ab[i][j]=='-')
				{
					c++;
				}
			}
		}
		if(c==0)
		{
			return 3;
		}


		return 0;


	}
	int score(char [][]ab,int level,char aib,char opo)  // Score
	{
		int a=whowon(ab);
		if(aib=='X')
		{


			if(a==1)
			{
				return 10-level;
			}
			else if(a==2)
			{
				return level-10;

			}
			else
			{
				return 0;
			}

		}
		else
		{
			if(a==1)
			{
				return level-10;
			}
			else if(a==2)
			{
				return 10-level;

			}
			else
			{
				return 0;
			}

		}
		//return 0;
	}

	//***************************************
	// MINMAX
	//*************************************

	int minmax(char[][]ab, int level,int []fmove,char aib,char opo)
	{
		if(whowon(ab)!=0 )
		{
			return score(ab,level,aib,opo);
		}
		else if(level==10)
		{
			return 0;
		}

		int [][]m=new int[9][2];

		int i,j,k;
		k=0;


		for(i=0;i<3;i++)          //finding the possible moves
		{
			for(j=0;j<3;j++)
			{
				if(ab[i][j]=='-')
				{
					m[k][0]=i;
					m[k][1]=j;
					k++;
				}
			}
		}


		if(level%2==0)  // for ai's move
		{
			int scor,fin;
			fin=-10000;
			for(i=0;i<k;i++)
			{
				//if(level==0)
				//	System.out.println("NEW VALUE ");

				ab[m[i][0]][m[i][1]]=aib;
				scor=minmax(ab,level+1,fmove,aib,opo);
				//System.out.println("Score="+scor+"cordinates:"+m[i][0]+","+ m[i][1]);
				/*
				if(level==0)
				{
					System.out.println("coordinates= "+m[i][0]+","+m[i][1]);
					System.out.println("Score= "+scor);
					System.out.println();
				}
				*/

				if(scor>fin && level==0)
				{
					fin=scor;
					fmove[0]=m[i][0];
					fmove[1]=m[i][1];
				}
				else if(scor>fin)
				{
					fin=scor;
				}
				ab[m[i][0]][m[i][1]]='-';

			}
			return fin;
		}

		else    // for oppenent's move
		{
			int scor,fin;
			fin=10000;
			for(i=0;i<k;i++)
			{
				ab[m[i][0]][m[i][1]]=opo;
				scor=minmax(ab,level+1,fmove,aib,opo);
				if(scor<fin )
				{
					fin=scor;
					//fmove[0]=m[i][0];
					//fmove[1]=m[i][1];
				}
				ab[m[i][0]][m[i][1]]='-';
			}
			return fin;

		}
	}


	//******************************************************************************************************************
	//*******************			GRID CLASS 					********************************************************
	//******************************************************************************************************************

	 class Board {
		private char [][]grid;

		Board()
		{
			grid=new char[3][3];
			int i,j;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					grid[i][j]='-';
				}
			}
		}

		public int put(int xx,int yy,char val)
		{

			if(xx<3 && yy<3 && grid[xx][yy]!='-')
			{
				return 0;
			}
			else if(xx<3 && yy<3)
			{
				grid[xx][yy]=val;
				return 1;
			}
			else return 0;
		}


		public void print()
		{
			int i,j;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					System.out.print(grid[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}

		//*********************************************************
		//      CHECKSTATE
		//*******************************************************

		public int checkState()
		{
			int i,j,c;
			c=0;

			for(i=0;i<3;i++)
			{
				c=0;
				for(j=0;j<3;j++)
				{
					if(grid[i][j]=='X')
					{
						c++;
					}
				}
				if(c==3)
				{
					//System.out.println("X Wins ");
					return 1;
				}
			}

			for(i=0;i<3;i++)
			{
				c=0;
				for(j=0;j<3;j++)
				{
					if(grid[j][i]=='X')
					{
						c++;
					}
				}
				if(c==3)
				{
					//System.out.println("X Wins ");
					return 1;
				}
			}

			c=0;
			for(i=0;i<3;i++)
			{
				if(grid[i][i]=='X')
					c++;
			}
			if(c==3)
			{
				//System.out.println("X Wins ");
				return 1;
			}


			c=0;
			for(i=0;i<3;i++)
			{
				if(grid[i][2-i]=='X')
					c++;
			}
			if(c==3)
			{
				//System.out.println("X Wins ");
				return 1;
			}


			//*************************
			//For O
			//************************

			for(i=0;i<3;i++)
			{
				c=0;
				for(j=0;j<3;j++)
				{
					if(grid[i][j]=='O')
					{
						c++;
					}
				}
				if(c==3)
				{
					//System.out.println("O Wins ");
					return 2;
				}
			}

			for(i=0;i<3;i++)
			{
				c=0;
				for(j=0;j<3;j++)
				{
					if(grid[j][i]=='O')
					{
						c++;
					}
				}
				if(c==3)
				{
					//System.out.println("O Wins ");
					return 2;
				}
			}

			c=0;
			for(i=0;i<3;i++)
			{
				if(grid[i][i]=='O')
					c++;
			}
			if(c==3)
			{
				//System.out.println("O Wins ");
				return 2;
			}


			c=0;
			for(i=0;i<3;i++)
			{
				if(grid[i][2-i]=='O')
					c++;
			}
			if(c==3)
			{
				//System.out.println("O Wins ");
				return 2;
			}

			int k;
			k=0;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(grid[i][j]=='-')
					{
						k++;
					}
				}
			}

			if(k==0)
			{
				return 3;     //no empty spaces i.e tie
			}



			return 0;        //empty spaces left

		}

		public char[][] get()
		{
			return grid;
		}


		/*


		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Board a=new Board();
			a.print();
			a.put(1, 1, 'X');
			a.print();
			a.put(2, 1, 'O');
			a.print();

		}
	*/
	}




	public static void main(String[] args)
	{

	//	System.out.println("HI_HI");
		DriverGui d=new DriverGui();


	}




}
