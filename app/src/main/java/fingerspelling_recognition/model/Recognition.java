package fingerspelling_recognition.model;

import fingerspelling_recognition.utils.ColorCode;
import fingerspelling_recognition.utils.HandJoint;

//「の」「り」「を」「ん」は未対応
public class Recognition {
	private HandJoint hand;
	private String str = null;
	private String text = null;
	private final String defaultText = "";

	//基本形のみ
	public void recognize() {
		if(hand.hasHand()) {
			if(e()) {
				if(b()) {
					if(a()) {
						if(r()) {
							str = "か";
						}else {
							if(t()) {
								str = "ら";
							}else {
								str = "う";
							}
						}
					}else {
						if(g()) {
							str = "と";
						}else {
							if(u()) {
								str = "は";
							}else {
								str = "ろ";
							}
						}
					}
				}else {
					if(a()) {
						str = "は";
					}else {
						if(h()) {
							str = "な";
						}else {
							str = "に";
						}
					}
				}
			}else {
				if(b()) {
					if(j()) {
						str = "ひ";
					}else {
						if(f()) {
							if(l()) {
								if(m()) {
									str = "け";
								}else {
									str = "き";
								}
							} else {
								str = "え";
							}
						}else {
							if(a()) {
								if(d()) {
									if(k()) {
										if(l()) {
											if(p()) {
												str = "る";
											}else {
												str = "れ";
											}
										}else {
											if(o()) {
												if(n()) {
													str = "つ";
												}else {
													if(r()) {
														str = "い";
													}else {
														str = "や";
													}
												}
											}else {
												str = "あ";
											}
										}
									}else {
										if(n()) {
											if(l()) {
												if(o()) {
													str = "け";
												}else {
													str = "わ";
												}
											}else {
												if(p()) {
													str = "め";
												}else {
													str = "つ";
												}
											}
										}else {
											if(l()) {
												str = "き";
											}else {
												if(i()) {
													str = "さ";
												}else {
													if(o()) {
														if(c()) {
															str = "ち";
														}else {
															str = "い";
														}
													}else {
														str = "せ";
													}
												}
											}
										}
									}
								}else {
									str ="て";
								}
							}else {
								if(d()) {
									if(g()) {
										if(n()) {
											str = "ゆ";
										}else {
											str = "も";
										}
									}else {
										if(q()) {
											str = "ぬ";
										}else {
											if(s()) {
												str = "こ";
											}else {
												str = "お";
											}
										}

									}
								}else {
									if(g()) {
										str = "ほ";
									}else {
										str = "こ";
									}
								}
							}
						}
					}
				}else {
					if(j()) {
						str = "そ";
					}else {
						if(d()) {
							if(h()) {
								if(k()) {
									if(l()) {
										if(p()) {
											str = "す";
										}else {
											str = "ふ";
										}
									}else{
										str = "へ";
									}
								}else {
									str = "ま";
								}
							}else {
								if(k()) {
									if(l()) {
										if(p()) {
											str = "し";
										}else {
											str = "む";
										}
									}else {
										str = "た";
									}
								}else {
									if(o()) {
										str = "よ";
									}else {
										str = "み";
									}
								}
							}
						}else {
							if(h()) {
								str = "ね";
							} else {
								str = "く";
							}

						}
					}
				}
			}
			if(text == null) {
				text = str;
			}else {
				text += str;
			}
			return;
		} else {
			return;
		}
	}

	//濁点の音があるものは濁点になる
	public void dakuten(){
		if(hand.hasHand()) {
			if(e()) {
				if(b()) {
					if(a()) {
						if(r()) {
							str = "が";
						}else {
							if(t()) {
								str = "ら";
							}else {
								str = "ゔ";
							}
						}
					}else {
						if(g()) {
							str = "ど";
						}else {
							if(u()) {
								str = "ば";
							}else {
								str = "ろ";
							}
						}
					}
				}else {
					if(a()) {
						str = "ば";
					}else {
						if(h()) {
							str = "な";
						}else {
							str = "に";
						}
					}
				}
			}else {
				if(b()) {
					if(j()) {
						str = "び";
					}else {
						if(f()) {
							if(l()) {
								if(m()) {
									str = "げ";
								}else {
									str = "ぎ";
								}
							} else {
								str = "え";
							}
						}else {
							if(a()) {
								if(d()) {
									if(k()) {
										if(l()) {
											if(p()) {
												str = "る";
											}else {
												str = "れ";
											}
										}else {
											if(o()) {
												if(n()) {
													str = "づ";
												}else {
													if(r()) {
														str = "い";
													}else {
														str = "や";
													}
												}
											}else {
												str = "あ";
											}
										}
									}else {
										if(n()) {
											if(l()) {
												if(o()) {
													str = "げ";
												}else {
													str = "わ";
												}
											}else {
												if(p()) {
													str = "め";
												}else {
													str = "づ";
												}
											}
										}else {
											if(l()) {
												str = "ぎ";
											}else {
												if(i()) {
													str = "ざ";
												}else {
													if(o()) {
														if(c()) {
															str = "ぢ";
														}else {
															str = "い";
														}
													}else {
														str = "ぜ";
													}
												}
											}
										}
									}
								}else {
									str ="で";
								}
							}else {
								if(d()) {
									if(g()) {
										if(n()) {
											str = "ゆ";
										}else {
											str = "も";
										}
									}else {
										if(q()) {
											str = "ぬ";
										}else {
											if(s()) {
												str = "ご";
											}else {
												str = "お";
											}
										}

									}
								}else {
									if(g()) {
										str = "ぼ";
									}else {
										str = "ご";
									}
								}
							}
						}
					}
				}else {
					if(j()) {
						str = "ぞ";
					}else {
						if(d()) {
							if(h()) {
								if(k()) {
									if(l()) {
										if(p()) {
											str = "ず";
										}else {
											str = "ぶ";
										}
									}else{
										str = "べ";
									}
								}else {
									str = "ま";
								}
							}else {
								if(k()) {
									if(l()) {
										if(p()) {
											str = "じ";
										}else {
											str = "む";
										}
									}else {
										str = "だ";
									}
								}else {
									if(o()) {
										str = "よ";
									}else {
										str = "み";
									}
								}
							}
						}else {
							if(h()) {
								str = "ね";
							} else {
								str = "ぐ";
							}

						}
					}
				}
			}
			if(text == null) {
				text = str;
			}else {
				text += str;
			}
			return;
		} else {
			return;
		}
	}

	//半濁点の音があるものは半濁点になる
	public void handakuten() {
		if(hand.hasHand()) {
			if(e()) {
				if(b()) {
					if(a()) {
						if(r()) {
							str = "か";
						}else {
							if(t()) {
								str = "ら";
							}else {
								str = "う";
							}
						}
					}else {
						if(g()) {
							str = "と";
						}else {
							if(u()) {
								str = "ぱ";
							}else {
								str = "ろ";
							}
						}
					}
				}else {
					if(a()) {
						str = "ぱ";
					}else {
						if(h()) {
							str = "な";
						}else {
							str = "に";
						}
					}
				}
			}else {
				if(b()) {
					if(j()) {
						str = "ぴ";
					}else {
						if(f()) {
							if(l()) {
								if(m()) {
									str = "け";
								}else {
									str = "き";
								}
							} else {
								str = "え";
							}
						}else {
							if(a()) {
								if(d()) {
									if(k()) {
										if(l()) {
											if(p()) {
												str = "る";
											}else {
												str = "れ";
											}
										}else {
											if(o()) {
												if(n()) {
													str = "つ";
												}else {
													if(r()) {
														str = "い";
													}else {
														str = "や";
													}
												}
											}else {
												str = "あ";
											}
										}
									}else {
										if(n()) {
											if(l()) {
												if(o()) {
													str = "け";
												}else {
													str = "わ";
												}
											}else {
												if(p()) {
													str = "め";
												}else {
													str = "つ";
												}
											}
										}else {
											if(l()) {
												str = "き";
											}else {
												if(i()) {
													str = "さ";
												}else {
													if(o()) {
														if(c()) {
															str = "ち";
														}else {
															str = "い";
														}
													}else {
														str = "せ";
													}
												}
											}
										}
									}
								}else {
									str ="て";
								}
							}else {
								if(d()) {
									if(g()) {
										if(n()) {
											str = "ゆ";
										}else {
											str = "も";
										}
									}else {
										if(q()) {
											str = "ぬ";
										}else {
											if(s()) {
												str = "こ";
											}else {
												str = "お";
											}
										}

									}
								}else {
									if(g()) {
										str = "ぽ";
									}else {
										str = "こ";
									}
								}
							}
						}
					}
				}else {
					if(j()) {
						str = "そ";
					}else {
						if(d()) {
							if(h()) {
								if(k()) {
									if(l()) {
										if(p()) {
											str = "す";
										}else {
											str = "ぷ";
										}
									}else{
										str = "ぺ";
									}
								}else {
									str = "ま";
								}
							}else {
								if(k()) {
									if(l()) {
										if(p()) {
											str = "し";
										}else {
											str = "む";
										}
									}else {
										str = "た";
									}
								}else {
									if(o()) {
										str = "よ";
									}else {
										str = "み";
									}
								}
							}
						}else {
							if(h()) {
								str = "ね";
							} else {
								str = "く";
							}

						}
					}
				}
			}
			if(text == null) {
				text = str;
			}else {
				text += str;
			}
			return;
		} else {
			return;
		}
	}

	public void setHand(HandJoint hand){
		this.hand = hand;
	}

	//相手に向いている面が手の平である
	public boolean a() {
		return hand.isFront();
	}

	//手の向きが上である
	public boolean b() {
		return hand.isUp();
	}

	//第一・第二関節で輪を作っている指がある
	public boolean c() {
		return hand.isPich();
	}

	//第一・第二関節が曲がっている指がある
	public boolean d() {
//		return !hand.isThumpDioStraight() && !hand.isThumpPipStraight() || !hand.isIndexDioStraight() && !hand.isIndexPipStraight() || !hand.isMiddleDioStraight() && !hand.isMiddlePipStraight() || !hand.isRingDioStraight() && !hand.isRingPipStraight() || !hand.isPinkyDioStraight() && !hand.isPinkyPipStraight();
		return !hand.isThumbStraight() || !hand.isIndexStraight() || !hand.isMiddleStraight() || !hand.isRingStraight() || !hand.isPinkyStraight();
	}

	//人差し指と中指の第三関節だけが伸びている
	public boolean e() {
		return !hand.isThumbStraight() && hand.isIndexMpStraight() && hand.isMiddleMpStraight() && !hand.isRingMpStraight() && !hand.isPinkyMpStraight();
	}

	//親指以外の第三関節が伸びている
	public boolean f() {
		return !hand.isThumbStraight() && hand.isIndexMpStraight() && hand.isMiddleMpStraight() && hand.isRingMpStraight() && hand.isPinkyMpStraight();
	}

	//相手に向いている面が手の甲である
	public boolean g() {
		return hand.isBack();
	}

	//手の向きが下である
	public boolean h() {
		return hand.isDown();
	}

	//全部の第一・第二関節が曲がっている
	public boolean i() {
		return !hand.isThumbStraight() && !hand.isIndexStraight() && !hand.isMiddleStraight() && !hand.isRingStraight() && !hand.isPinkyStraight();
	}

	//人差し指以外の第一・第二関節が曲がっている
	public boolean j() {
//		return !hand.isThumpDioStraight() && !hand.isThumpPipStraight() && hand.isIndexDioStraight() && hand.isIndexPipStraight() && !hand.isMiddleDioStraight() && !hand.isMiddlePipStraight() && !hand.isRingDioStraight() && !hand.isRingPipStraight() && !hand.isPinkyDioStraight() && !hand.isPinkyPipStraight();
		return !hand.isThumbStraight() && hand.isIndexStraight() && !hand.isMiddleStraight() && !hand.isRingStraight() && !hand.isPinkyStraight();
	}

	//親指が完全に伸びている
	public boolean k() {
		return hand.isThumbStraight();
	}

	//人差し指が完全に伸びている
	public boolean l() {
		return hand.isIndexStraight();
	}

	//中指が完全に伸びている
	public boolean m() {
		return hand.isMiddleStraight();
	}

	//薬指が完全に伸びている
	public boolean n() {
		return hand.isRingStraight();
	}

	//小指が完全に伸びている
	public boolean o() {
		return hand.isPinkyStraight();
	}

	//中指の第三関節が伸びている
	public boolean p() {
		return hand.isMiddleMpStraight();
	}

	//人差し指の第三関節が伸びている
	public boolean q() {
		return hand.isIndexMpStraight();
	}

	//親指が中指についている
	public boolean r() {
		return hand.isNearK();
	}

	//親指以外で第一と第二関節が伸びている指がある
	public boolean s() {
		return hand.isIndexDioStraight() && hand.isIndexPipStraight() || hand.isMiddleDioStraight() && hand.isMiddlePipStraight() || hand.isRingDioStraight() && hand.isRingPipStraight() || hand.isPinkyDioStraight() && hand.isPinkyPipStraight();
	}

	//人差し指と中指が交差している
	public boolean t() {
		return hand.isIntersect();
	}

	public boolean u() {
		return hand.isIndexPipStraight() && hand.isMiddlePipStraight();
	}

	public String getText() {
		if(text != null) {
			return text;
		} else {
			return defaultText;
		}
	}

	public String getString() {
		return str;
	}

	public void delete() {
		text = null;
	}

	public String results() {
		String result = ColorCode.GREEN + ">>recognition: " + getText() + ColorCode.END + "\n";
		return result;
	}
}
