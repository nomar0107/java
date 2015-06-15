import java.util.ArrayList;
import java.util.Date;
import java.io.*;

import javax.swing.*;

import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class parkingmanager extends JFrame {
	static ArrayList<owner> list = new ArrayList<owner>();
	static String timerBuffer; // 04:11:15 ���� ��� �ð� ���ڿ��� ����� ���� ����

	parkingmanager() {

		JFrame jf = new JFrame("���Ұ���â");
		setTitle("��������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		JTextField[][] bpcnum = new JTextField[4][5];
		JTextField[][] busetime = new JTextField[4][5];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				bpcnum[i][j] = new JTextField(Integer.toString((i) * 5
						+ (j + 1)));
				bpcnum[i][j].setEditable(false);
				bpcnum[i][j].setLocation(i * 120, j * 120);
				bpcnum[i][j].setSize(119, 60);

				add(bpcnum[i][j]);

			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				busetime[i][j] = new JTextField();
				busetime[i][j].setEditable(false);
				busetime[i][j].setLocation(i * 120, (2 * j + 1) * 60);
				busetime[i][j].setSize(119, 50);

				add(busetime[i][j]);

			}
		}
		JButton a0 = new JButton("��Ȳ ���");
		a0.setLocation(850, 0);
		a0.setSize(100, 50);
		add(a0);
		JButton a1 = new JButton("�մ� �߰�");
		a1.setLocation(850, 50);
		a1.setSize(100, 50);
		add(a1);
		JButton a2 = new JButton("��� ����");
		a2.setLocation(850, 100);
		a2.setSize(100, 50);
		add(a2);
		JButton a3 = new JButton("��� ����");
		a3.setLocation(850, 150);
		a3.setSize(100, 50);
		add(a3);
		JButton a5 = new JButton("�ڸ� �̵�");
		a5.setLocation(850, 200);
		a5.setSize(100, 50);
		add(a5);
		JButton a6 = new JButton("ȭ�� ����");
		a6.setLocation(850, 250);
		a6.setSize(100, 50);
		add(a6);
		JButton a7 = new JButton("���� ����");
		a7.setLocation(850, 300);
		a7.setSize(100, 50);
		add(a7);
		JButton a8 = new JButton("���� ����");
		a8.setLocation(850, 350);
		a8.setSize(100, 50);
		add(a8);
		JButton a9 = new JButton("���� ����");
		a9.setLocation(850, 400);
		a9.setSize(100, 50);
		add(a9);
		JButton a10 = new JButton("�մ� �˻�");
		a10.setLocation(850, 450);
		a10.setSize(100, 50);
		add(a10);
		JButton a11 = new JButton("�մ� ����");
		a11.setLocation(850, 500);
		a11.setSize(100, 50);
		add(a11);
		JTextArea t2 = new JTextArea();
		t2.setEditable(false);
		t2.setLocation(500, 0);
		t2.setSize(350, 600);
		JScrollPane jsp = new JScrollPane(t2);
		jsp.setLocation(500, 0);
		jsp.setSize(350, 600);
		add(jsp);
		setSize(1000, 700);
		setVisible(true);
		a11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame jf1 = new JFrame("�մԻ���");
				JPanel p3 = new JPanel(new GridLayout(2, 1));
				JLabel jl1 = new JLabel("�մ�����");
				JComboBox<String> cb1 = new JComboBox<String>();
				JButton jb = new JButton("�����ϱ�");
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getpknum() == 0) {
						cb1.addItem(list.get(i).getownername() + " "
								+ list.get(i).getcarnum());
					}
				}
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String carnum = (String) cb1.getSelectedItem();
						String carnumlist[] = carnum.split(" ");
						list.remove(listindexfindbycarnum(carnumlist[1]));
						JOptionPane.showMessageDialog(null, "���� �Ϸ�Ǿ����ϴ�",
								"�������", JOptionPane.PLAIN_MESSAGE);

					}
				});
				p3.add(jl1);
				p3.add(cb1);
				p3.add(jb);
				jf1.getContentPane().add(p3);
				jf1.pack();
				jf1.setVisible(true);
			}
		});
		a10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame jf1 = new JFrame("���� �˻�");
				JPanel p3 = new JPanel(new GridLayout(2, 1));
				JLabel jl1 = new JLabel("�� ��ȣ");
				JButton b1 = new JButton("�� ��ȣ�� �˻�");
				b1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JComboBox<String> cb1 = new JComboBox<String>();
						for (int i = 0; i < list.size(); i++) {
							cb1.addItem(list.get(i).getcarnum());
						}

						JFrame jf2 = new JFrame("��ȣ�˻�");
						JPanel p4 = new JPanel(new GridLayout(2, 2));
						JLabel jl2 = new JLabel("������ȣ");
						JButton b3 = new JButton("�˻�");
						b3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								String carnum = (String) cb1.getSelectedItem();
								int index = listindexfindbycarnum(carnum);
								if (list.get(index).getpknum() != 0) {
									int oldtime = list.get(index).getoldtime();
									int UseTime = (int) System
											.currentTimeMillis()
											/ 1000
											- oldtime;
									calcultime(index, UseTime, list.get(index)
											.getrtime());
								}
								JFrame jf2 = new JFrame("��ȣ�˻�");
								JPanel p4 = new JPanel(new GridLayout(1, 1));
								JTextArea tf1 = new JTextArea();

								tf1.append("����� ������ȣ : "
										+ list.get(index).getcarnum() + "\n");
								tf1.append("����� �̸� : "
										+ list.get(index).getownername() + "\n");
								tf1.append("�����ð� : "
										+ secToHHMMSS(list.get(index)
												.getrtime()) + "\n");
								tf1.append("���ð� : "
										+ secToHHMMSS(list.get(index)
												.getutime()) + "\n");
								if (list.get(index).getpknum() != 0) {
									tf1.append("���� ����������� ���� : "
											+ list.get(index).getpknum()
											+ "�� ���� ���� �����\n");
								} else {
									tf1.append("���� ����������� ���� : ����� �ƴ�\n");
								}
								p4.add(tf1);
								jf2.getContentPane().add(p4);
								jf2.pack();
								jf2.setVisible(true);
							}
						});
						p4.add(jl2);
						p4.add(cb1);
						p4.add(b3);
						jf2.getContentPane().add(p4);
						jf2.pack();
						jf2.setVisible(true);
					}
				});

				JButton b2 = new JButton("�̸����� �˻�");
				b2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						JComboBox<String> cb2 = new JComboBox<String>();

						for (int i = 0; i < list.size(); i++) {
							cb2.addItem(list.get(i).getownername());
						}
						JFrame jf2 = new JFrame("�̸� �˻�");
						JPanel p4 = new JPanel(new GridLayout(2, 2));
						JLabel jl2 = new JLabel("�̸�");
						JButton b3 = new JButton("�˻�");

						b3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								String name = (String) cb2.getSelectedItem();
								int index = listindexfindbyname(name);
								JFrame jf2 = new JFrame("�̸��˻�");
								JPanel p4 = new JPanel(new GridLayout(1, 1));
								JTextArea tf1 = new JTextArea();
								if (list.get(index).getpknum() != 0) {
									int oldtime = list.get(index).getoldtime();
									int UseTime = (int) System
											.currentTimeMillis()
											/ 1000
											- oldtime;
									calcultime(index, UseTime, list.get(index)
											.getrtime());
								}
								tf1.append("����� ������ȣ : "
										+ list.get(index).getcarnum() + "\n");
								tf1.append("����� �̸� : "
										+ list.get(index).getownername() + "\n");
								tf1.append("�����ð� : "
										+ secToHHMMSS(list.get(index)
												.getrtime()) + "\n");
								tf1.append("���ð� : "
										+ secToHHMMSS(list.get(index)
												.getutime()) + "\n");
								if (list.get(index).getpknum() != 0) {
									tf1.append("���� ����������� ���� : "
											+ list.get(index).getpknum()
											+ " �� ���� ���� �����\n");
								} else {
									tf1.append("���� ����������� ���� : ����� �ƴ�\n");
								}
								p4.add(tf1);
								jf2.getContentPane().add(p4);
								jf2.pack();
								jf2.setVisible(true);
							}
						});

						p4.add(jl2);
						p4.add(cb2);
						p4.add(b3);
						jf2.getContentPane().add(p4);
						jf2.pack();
						jf2.setVisible(true);
					}
				});
				p3.add(b1);
				p3.add(b2);
				jf1.getContentPane().add(p3);
				jf1.pack();
				jf1.setVisible(true);
			}
		});
		a0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t2.append("���� ����\n");
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getpknum() != 0) {
						int oldtime = list.get(i).getoldtime();
						int UseTime = (int) System.currentTimeMillis() / 1000
								- oldtime;
						calcultime(i, UseTime, list.get(i).getrtime());
					}
					t2.append((i + 1) + ")" + list.get(i).toString() + "\n");
				}
			}
		});
		
		a1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ownername = JOptionPane.showInputDialog("�����̸��� �Է��ϼ���");
				String carvar = JOptionPane.showInputDialog("������ �Է��ϼ���");
				String carnum = JOptionPane.showInputDialog("������ȣ�� �Է��ϼ���");
				String birthyear = JOptionPane
						.showInputDialog("������ �¾ �⵵�� �Է��ϼ���");
				if (listcarnumcheck(carnum)) {
					addCustomer(carnum + "(" + listhowmanycarnum(carnum) + ")",
							carvar, ownername, Integer.parseInt(birthyear));
				} else {
					addCustomer(carnum, carvar, ownername,
							Integer.parseInt(birthyear));
				}
				t2.append(ownername + "�մ��� �߰��Ǿ����ϴ�\n");

			}
		});
		a2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame jf1 = new JFrame("��� ����â");
				JPanel p3 = new JPanel(new GridLayout(3, 2));
				JLabel jl1 = new JLabel("������ȣ");
				JLabel jl2 = new JLabel("����������ȣ");
				JComboBox<String> cb1 = new JComboBox<String>();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getpknum() == 0) {
						cb1.addItem(list.get(i).getownername() + " "
								+ list.get(i).getcarnum());
					}
				}
				JComboBox<Integer> cb2 = new JComboBox<Integer>();
				for (int i = 0; i < 20; i++) {
					if (!listpknumcheck(i + 1)) {
						cb2.addItem((i + 1));
					}
				}
				JButton j1 = new JButton("�̿����");
				j1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "�����۵Ǿ����ϴ�.",
								"������", JOptionPane.PLAIN_MESSAGE);
						String carnum = (String) cb1.getSelectedItem();
						String carnumlist[] = carnum.split(" ");
						int pknum = (int) cb2.getSelectedItem();
						for (int i = 0; i < 4; i++) {
							for (int j = 0; j < 5; j++) {
								if (i * 5 + j + 1 == pknum) {
									bpcnum[i][j].setText(pknum + ")" + carnum);
									Date now = new Date();
									String a = now.toString();
									String[] b = a.split(" ");
									startUse(carnumlist[1], pknum);
								}

							}

						}
					}
				});

				p3.add(jl1);
				p3.add(cb1);
				p3.add(jl2);
				p3.add(cb2);
				p3.add(j1);
				jf1.getContentPane().add(p3);
				jf1.pack();
				jf1.setVisible(true);
			}
		});
		a3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame jf2 = new JFrame("��� ����â");
				JPanel p3 = new JPanel(new GridLayout(2, 2));
				JLabel jl1 = new JLabel("��������");
				JButton jb = new JButton("��� ����");
				JComboBox<String> cb1 = new JComboBox<String>();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getpknum() != 0) {
						cb1.addItem(list.get(i).getownername() + " "
								+ list.get(i).getcarnum());
					}
				}
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String carnum = (String) cb1.getSelectedItem();
						String carnumlist[] = carnum.split(" ");
						int index = listindexfindbycarnum(carnumlist[1]);
						int index2 = listindexfindbyname(carnumlist[0]);
						int oldtime = list.get(index).getoldtime();
						int UseTime = (int) System.currentTimeMillis() / 1000
								- oldtime;
						list.get(index).setutime(UseTime);
						if (list.get(index).getutime()
								- list.get(index).getrtime() > 0) {
							int costtime = list.get(index).getutime()
									- list.get(index).getrtime();
							int cost = timetomoney(costtime);

							JOptionPane.showMessageDialog(null,
									"����� ����Ǿ����ϴ�. ��� �ݾ��� " + cost + "�� �Դϴ�.",
									"�������", JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null,
									"����� ����Ǿ����ϴ�. ��� �ݾ��� 0�� �Դϴ�.", "�������",
									JOptionPane.PLAIN_MESSAGE);
						}
						for (int i = 0; i < 4; i++) {
							for (int j = 0; j < 5; j++) {
								if (i * 5 + j + 1 == list.get(index).getpknum()) {

									bpcnum[i][j].setText(Integer.toString(list
											.get(index).getpknum()));
								}
							}
						}
						EndUse(carnumlist[1], list.get(index).getpknum());
					}
				});

				p3.add(jl1);
				p3.add(cb1);
				p3.add(jb);
				jf2.getContentPane().add(p3);
				jf2.pack();
				jf2.setVisible(true);

			}
		});

		a5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame jf2 = new JFrame("�ڸ��̵�â");
				JPanel p3 = new JPanel(new GridLayout(3, 2));
				JLabel lb1 = new JLabel("��������");
				JLabel lb2 = new JLabel("�̵��� �ڸ�");
				JButton jb = new JButton("�ڸ��̵�");
				JComboBox<String> cb1 = new JComboBox<String>();
				JComboBox<Integer> cb2 = new JComboBox<Integer>();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getpknum() != 0) {
						cb1.addItem(list.get(i).getownername() + " "
								+ list.get(i).getcarnum());
					}
				}
				for (int i = 0; i < 20; i++) {
					if (!listpknumcheck(i + 1)) {
						cb2.addItem(i + 1);
					}
				}
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "�ڸ��� ����Ǿ����ϴ�.",
								"�ڸ�����", JOptionPane.PLAIN_MESSAGE);
						String carnum = (String) cb1.getSelectedItem();
						String carnumlist[] = carnum.split(" ");
						int pknum = (int) cb2.getSelectedItem();
						int index = listindexfindbycarnum(carnumlist[1]);
						for (int i = 0; i < 4; i++) {
							for (int j = 0; j < 5; j++) {
								if (i * 5 + j + 1 == list.get(index).getpknum()) {

									bpcnum[i][j].setText(Integer.toString(list
											.get(index).getpknum()));
								}
							}
						}
						for (int i = 0; i < 4; i++) {
							for (int j = 0; j < 5; j++) {
								if (i * 5 + j + 1 == pknum) {

									bpcnum[i][j].setText(Integer
											.toString(pknum) + ")" + carnum);
								}
							}
						}
						ChangePos(carnumlist[1], list.get(index).getpknum(),
								pknum);
					}
				});
				p3.add(lb1);
				p3.add(cb1);
				p3.add(lb2);
				p3.add(cb2);
				p3.add(jb);
				jf2.getContentPane().add(p3);
				jf2.pack();
				jf2.setVisible(true);

			}
		});
		a6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 5; j++) {
						if (listpknumcheck(i * 5 + j + 1)) {
							int index = listindexfindbypknum(i * 5 + j + 1);
							bpcnum[i][j].setText(i * 5 + j + 1 + ")"
									+ list.get(index).getownername() + " "
									+ list.get(index).getcarnum());
							int old = list.get(index).getoldtime();
							int usetime = (int) System.currentTimeMillis()
									/ 1000 - old;
							busetime[i][j].setText("���ð� : "
									+ secToHHMMSS(usetime));
						} else {
							busetime[i][j].setText("");
						}

					}
				}
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 5; j++) {

					}
				}
			}
		});

		a7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Readfile();
				t2.append("�մ� ����\n");
				for (int i = 0; i < list.size(); i++) {
					t2.append(list.get(i).toString() + "\n");
				}
				t2.append("�մ����������� �о�Խ��ϴ�\n");
			}
		});
		a8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Savefile();
				t2.append("�մ� ����\n");
				for (int i = 0; i < list.size(); i++) {
					t2.append(list.get(i).toString() + "\n");
				}
				t2.append("�մ������� ���Ͽ� �����߽��ϴ�\n");
			}
		});
		a9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel p3 = new JPanel(new GridLayout(3, 2));
				JComboBox<String> cb = new JComboBox<String>();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getpknum() == 0) {
						cb.addItem(list.get(i).getownername() + " "
								+ list.get(i).getcarnum());
					}
				}
				JButton j1 = new JButton("5000�� - 1��");
				JButton j2 = new JButton("10000�� -3��");
				JButton j3 = new JButton("20000�� - 7��");
				j1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.",
								"�����Ϸ�", JOptionPane.PLAIN_MESSAGE);
						String carname = (String) cb.getSelectedItem();
						String carnamelist[] = carname.split(" ");
						payFirst(carnamelist[1], 5000);
					}
				});
				j2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.",
								"�����Ϸ�", JOptionPane.PLAIN_MESSAGE);
						String carname = (String) cb.getSelectedItem();
						String carnamelist[] = carname.split(" ");
						payFirst(carnamelist[1], 10000);

					}
				});
				j3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.",
								"�����Ϸ�", JOptionPane.PLAIN_MESSAGE);
						String carname = (String) cb.getSelectedItem();
						String carnamelist[] = carname.split(" ");
						payFirst(carnamelist[1], 20000);
					}
				});
				String str = (String) cb.getSelectedItem();
				p3.add(cb);
				p3.add(j1);
				p3.add(j2);
				p3.add(j3);

				jf.getContentPane().add(p3);
				jf.pack();
				jf.setVisible(true);
			}
		});
	}

	public static void Readfile() {
		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try {
			fin = new FileInputStream("booklist2.dat");
			ois = new ObjectInputStream(fin);
			list = (ArrayList<owner>) ois.readObject();
		} catch (Exception ex) {
		} finally {
			try {
				ois.close();
				fin.close();
			} catch (IOException ioe) {
			}
		}
	}

	public static void Savefile() {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			fout = new FileOutputStream("booklist2.dat");
			oos = new ObjectOutputStream(fout);

			oos.writeObject(list);
			oos.reset();
			oos.writeObject(list);
			oos.reset();

		} catch (Exception ex) {
		} finally {
			try {
				oos.close();
				fout.close();
			} catch (IOException ioe) {
			}
		}
	}

	public void addCustomer(String carnum, String carvar, String ownername,
			int birthyear) {
		owner c = new owner();
		c.setcarnum(carnum);
		c.setoldtime(0);
		c.setpknum(0);
		c.setcarvar(carvar);
		c.setrtime(0);
		c.setutime(0);
		c.setownername(ownername);
		c.setbirthyear(birthyear);
		list.add(c);
	}

	

	public void startUse(String carnum, int pknum) {
		// ��� ����
		int oldTime = (int) System.currentTimeMillis() / 1000;
		int index = listindexfindbycarnum(carnum);
		list.get(index).setoldtime(oldTime);
		list.get(index).setpknum(pknum);
	}

	public void EndUse(String carnum, int pknum) {
		int index = 0;
		index = listindexfindbycarnum(carnum);
		list.get(index).setutime(0);
		list.get(index).setpknum(0);

	}

	public void calcultime(int index, int utime, int rtime) {
		if (utime >= rtime) {
			list.get(index).setutime(utime - rtime);
			list.get(index).setrtime(0);
		} else if (utime < rtime) {
			list.get(index).setrtime(rtime - utime);
			list.get(index).setutime(0);
		}
	}

	public void payFirst(String carnum, int cost) {

		int index = listindexfindbycarnum(carnum);

		if (cost == 5000) {
			list.get(index).setrtime(24 * 60 * 60);
		} else if (cost == 10000) {
			list.get(index).setrtime(3 * 24 * 3600);
		} else {
			list.get(index).setrtime(7 * 24 * 3600);
		}
	}

	

	public void ChangePos(String carnum, int lastpos, int newpos) {
		int index = listindexfindbycarnum(carnum);
		list.get(index).setpknum(newpos);

	}

	public String secToHHMMSS(int secs) {
		int hour, min, sec, day;

		sec = secs % 60;
		min = secs / 60 % 60;
		hour = secs / 60 / 60 % 24;
		day = secs / 60 / 60 / 24;
		return String.format("%02d�� %02d:%02d:%02d", day, hour, min, sec);
	}

	public Boolean listpknumcheck(int pknum) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getpknum() == pknum) {
				return true;
			}
		}
		return false;
	}

	public Boolean listcarnumcheck(String carnum) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getcarnum().equals(carnum)) {
				return true;
			}
		}
		return false;
	}

	public int listhowmanycarnum(String carnum) {
		int num = 0;

		for (int i = 0; i < list.size(); i++) {
			if (num == 0) {
				if (list.get(i).getcarnum().equals(carnum)) {
					num++;
				}
			} else {
				if (list.get(i).getcarnum().equals(carnum + "(" + num + ")")) {
					num++;
				}
			}
		}

		return num;
	}

	public int listindexfindbycarnum(String carnum) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getcarnum().equals(carnum)) {
				return i;
			}
		}
		return -1;
	}

	public int listindexfindbyname(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getownername().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public int listindexfindbypknum(int pknum) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getpknum() == pknum) {
				return i;
			}
		}
		return -1;
	}

	public int timetomoney(int time) {
		if (time % 3600 == 0) {
			int rtime = time / 3600;
			return rtime * 1000;
		} else if (time / 3600 == 0) {
			return 1000;
		} else {
			int rtime = time / 3600;
			int mtime = time / 60 % 60;
			int mmtime = mtime / 10;
			return rtime * 1000 + mmtime * 100;
		}

	}

	public static void main(String[] args) {
		parkingmanager pm = new parkingmanager();

	}

	/*
	 * public class pcManager { private static ArrayList<PC> plist = new
	 * ArrayList<PC>(); private static ArrayList<Customer> list = new
	 * ArrayList<Customer>(); static String timerBuffer; // 04:11:15 ���� ��� �ð�
	 * ���ڿ��� ����� ���� ����
	 * 
	 * public void setPC() { for (int i = 1; i <= 20; i++) { PC tpc = new PC();
	 * tpc.setPCnum(i); plist.add(tpc); } }
	 * 
	 * public void addCustomer(String id, String pw) { Customer c = new
	 * Customer(); c.setId(id); c.setPw(pw); c.setRtime(0); c.setUtime(0);
	 * c.setMileage(0); list.add(c); }
	 * 
	 * public String getIDofPC(int pcn) { return plist.get(pcn).getID(); }
	 * 
	 * public String toString() {
	 * 
	 * String a = null; for (int i = 0; i < list.size(); i++) { a = a +
	 * list.get(i).toString() + "\n"; } return a; }
	 * 
	 * public void startUse(String id, int pcn) { // ��� ���� int temp = 0; int
	 * oldTime = (int) System.currentTimeMillis() / 1000; int index = 0; for
	 * (int i = 0; i < list.size(); i++) { if (list.get(i).getId().equals(id)) {
	 * index = i; break; } list.get(index).setOldtime(oldTime);
	 * list.get(index).setPcnum(pcn); } } public void setIDofPC(String ID ,int
	 * pcn) { plist.get(pcn).setID(ID); } public void EndUse(String id, int
	 * pcnum) {
	 * 
	 * for (int i = 1; i <= 20; i++) { if (plist.get(i).getPCnum() == pcnum) {
	 * if (plist.get(i).getID().equals(id)) { plist.get(i).setID(null); } } }
	 * int index = 0; for (int i = 0; i < list.size(); i++) { if
	 * (list.get(i).getId().equals(id)) { index = i; break; } int oldtime =
	 * list.get(index).getOldtime(); int UseTime = (int)
	 * System.currentTimeMillis() / 1000 - oldtime;
	 * list.get(index).setUtime(UseTime); } }
	 * 
	 * public void payFirst(String id, int cost) { int index = 0; for (int i =
	 * 0; i < list.size(); i++) { if (list.get(i).getId().equals(id)) { index =
	 * i; break; } int tempRtime = list.get(index).getRtime(); if (cost % 1000
	 * != 0) { list.get(index).setRtime(tempRtime + cost / 1000); // 1000��������
	 * �޽��ϴ�. } else if (cost % 1000 == 0) { list.get(index).setRtime(tempRtime +
	 * cost / 1000); }
	 * 
	 * } }
	 * 
	 * public void payLast(String id, int cost) { int index = 0; for (int i = 0;
	 * i < list.size(); i++) { if (list.get(i).getId().equals(id)) { index = i;
	 * break; } int tempUtime = list.get(index).getUtime(); if (tempUtime % 1 !=
	 * 0) { cost = cost - 1000; if (cost >= 0) { // �Ž�����
	 * list.get(index).setUtime(0);
	 * 
	 * } else if (cost < 0) { // ������
	 * 
	 * } } }
	 * 
	 * }
	 * 
	 * public void SearchCus(String id) {
	 * 
	 * }
	 * 
	 * public void ChangePos(String id, int lastpos, int newpos) { for (int i =
	 * 1; i <= 20; i++) { if (plist.get(i).getPCnum() == lastpos) { if
	 * (plist.get(i).getID().equals(id)) { for (int j = 1; j <= 20; j++) { if
	 * (plist.get(j).getID().equals(null)) { plist.get(i).setID(null);
	 * plist.get(j).setID(id); } } } } } }
	 * 
	 * public void ReadFile() { FileInputStream fin = null; ObjectInputStream
	 * ois = null; try { fin = new FileInputStream("booklist.dat"); ois = new
	 * ObjectInputStream(fin);
	 * 
	 * list = (ArrayList) ois.readObject(); } catch (Exception ex) { } finally {
	 * try { ois.close(); fin.close(); } catch (IOException ioe) { } }
	 * 
	 * }
	 * 
	 * public void SaveFile() { FileOutputStream fout = null; ObjectOutputStream
	 * oos = null;
	 * 
	 * try { fout = new FileOutputStream("booklist.dat"); oos = new
	 * ObjectOutputStream(fout);
	 * 
	 * oos.writeObject(list); oos.reset(); oos.writeObject(list); oos.reset();
	 * 
	 * System.out.println("����Ǿ����ϴ�");
	 * 
	 * } catch (Exception ex) { } finally { try { oos.close(); fout.close(); }
	 * catch (IOException ioe) { } } }
	 * 
	 * public void secToHHMMSS(int secs) { int hour, min, sec;
	 * 
	 * sec = secs % 60; min = secs / 60 % 60; hour = secs / 3600;
	 * 
	 * timerBuffer = String.format("%02d:%02d:%02d", hour, min, sec);
	 */

	// TODO Auto-generated method stub

}
