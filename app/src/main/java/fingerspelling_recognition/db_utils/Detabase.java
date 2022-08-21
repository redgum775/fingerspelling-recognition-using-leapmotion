package fingerspelling_recognition.db_utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fingerspelling_recognition.utils.Theme;

public class Detabase {
	Theme theme = new Theme();

	String dbname = "src/main/resources/fingerspelling_recognition/db/experiments.db"; // 利用するデータベースファイル
    Connection conn = null;
    Statement stmt = null;

    public void creat() {
		creatExperimentsTable();
		creatResultsTable();
		creatUsersTable();
    }

	private void tryConnect() throws SQLException {
		conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
		stmt = conn.createStatement();
	}

	private void tryClose() throws SQLException{
		stmt.close();
		conn.close();
	}

	public void creatExperimentsTable() {
        try {
        	tryConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS experiments (experiment_id INTEGER PRIMARY KEY, user_id INTEGER, experiment_at TIMESTAMP DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime')))");
            System.out.println("実験テーブル作成");
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
        }
	}

	public void creatResultsTable() {
        try {
        	tryConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS results (result_no INTEGER PRIMARY KEY, experiment_id INTEGER, theme TEXT, input TEXT, is_correct_answer INTEGER, update_at TIMESTAMP DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime')))");
            System.out.println("結果テーブル作成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
        }
	}

	public void creatUsersTable() {
        try {
        	tryConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (user_id INTEGER PRIMARY KEY, user_sex INTEGER, hand INTEGER, years INTEGER, user_at TIMESTAMP DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime')))");
            System.out.println("ユーザテーブル作成");
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
        }
	}

	public int startExperoments(int userID) {
		int experimentId = 0;
        try {
        	tryConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO experiments(user_id) VALUES(" + userID + ")");
            System.out.println("実験開始");

            ResultSet rs = stmt.executeQuery("SELECT MAX(experiment_id) experiment_id FROM experiments");
            System.out.println("ID拾得");
            while (rs.next()) {
                experimentId = rs.getInt("experiment_id");
            }
            rs.close();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
        }
        System.out.println("experimentId: " + experimentId );
        return experimentId;
	}

	public int updateUser(int gender, int hand, int year) {
		int userId = 0;
		try {
			tryConnect();
			stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO users(user_sex, hand, years) VALUES(" + gender + ", '" + hand + "', " + year + ")");

            ResultSet rs = stmt.executeQuery("SELECT MAX(user_id) user_id FROM users");
            System.out.println("ID拾得");
            while (rs.next()) {
                userId = rs.getInt("user_id");
            }
            rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
        System.out.println("userId: " + userId );
		return userId;
	}

	public void updateResult(int experiment_id, String theme, String input, boolean isCorrectAnswer) {
		try {
			tryConnect();
			stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO results(experiment_id,theme,input,is_correct_answer) VALUES(" + experiment_id + ", '" + theme + "', '" + input + "', " + isCorrectAnswer + ")");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	public void resultsList(int id) {
		try {
			tryConnect();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM results where experiment_id = " + id);
			//結果のログ出力
			while (rs.next()) {
				int resultNo = rs.getInt("result_no");
				int experimentId = rs.getInt("experiment_id");
				String theme = rs.getString("theme");
				String input = rs.getString("input");
				int isCorrectAnswer = rs.getInt("is_correct_answer");
				String updateAt = rs.getString("update_at");

				System.out.println(resultNo + "\t" + experimentId + "\t" + theme + "\t" + input + "\t" + isCorrectAnswer + "\t" + updateAt);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	public void allResultsList() {
		try {
			tryConnect();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM results");
			//結果のログ出力
			while (rs.next()) {
				int resultNo = rs.getInt("result_no");
				int experimentId = rs.getInt("experiment_id");
				String theme = rs.getString("theme");
				String input = rs.getString("input");
				int isCorrectAnswer = rs.getInt("is_correct_answer");
				String updateAt = rs.getString("update_at");

				System.out.println(resultNo + "\t" + experimentId + "\t" + theme + "\t" + input + "\t" + isCorrectAnswer + "\t" + updateAt);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	public void deleteResult(int id) {
		try {
			tryConnect();
			stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM results WHERE experiment_id = " + id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	public void deleteAllResult(int head, int end) {
		try {
			tryConnect();
			stmt = conn.createStatement();
	        stmt.executeUpdate("DELETE FROM results WHERE result_no BETWEEN " + head + " AND " + end );	//WHEREをいじることで削除する要素を変更
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	    	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	public void getUserTable() {
		try {
			tryConnect();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");
			//結果のログ出力
			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				int user_sex = rs.getInt("user_sex");
				int hand = rs.getInt("hand");
				int year = rs.getInt("years");
				String updateAt = rs.getString("user_at");

				System.out.println(user_id + "\t" + user_sex + "\t" + hand + "\t" + year + "\t" + updateAt);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	public void getAllRate() {
		theme.moveHead();
		while(theme.hasNext()) {
			getRate(theme.getNextTheme());
		}
	}

	public void getUserRate(String id) {
		theme.moveHead();
		while(theme.hasNext()) {
			getRate2(theme.getNextTheme(),id);
		}
	}

	public void getRate(String s) {
		try {
			tryConnect();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT theme, COUNT(theme) AS numQuestion FROM results WHERE theme = '" + s + "' GROUP BY theme");

			//結果のログ出力

			String theme = rs.getString("theme");
			int numQuestion = rs.getInt("numQuestion");


			ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) AS answer FROM results WHERE theme = '" + s + "' AND is_correct_answer = 1");
			int answer = rs2.getInt("answer");

			System.out.println(theme + "\t" + numQuestion + "\t" + answer + "\t" + String.format("%.1f",(((double)answer/numQuestion)*100)) + "%");


		}catch(Exception e) {
			e.printStackTrace();
		}finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	public void getRate2(String s, String id) {
		try {
			tryConnect();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT theme, COUNT(theme) AS numQuestion FROM results WHERE theme = '" + s + "' AND experiment_id " + id + " GROUP BY theme");

			//結果のログ出力
			String theme = rs.getString("theme");
			int numQuestion = rs.getInt("numQuestion");


			ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) AS answer FROM results WHERE theme = '" + s + "' AND is_correct_answer = 1 AND experiment_id " + id);
			int answer = rs2.getInt("answer");

			System.out.println(theme + "\t" + numQuestion + "\t" + answer + "\t" + String.format("%.1f",(((double)answer/numQuestion)*100)) + "%");


		}catch(Exception e) {
			e.printStackTrace();
		}finally {
        	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	//特定のお題に対する詳細データ
	public void getStringRestultList(String s) {
		try {
		tryConnect();
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM results WHERE theme = '" + s + "'");

		//結果のログ出力
		while (rs.next()) {
			int resultNo = rs.getInt("result_no");
			int experimentId = rs.getInt("experiment_id");
			String theme = rs.getString("theme");
			String input = rs.getString("input");
			int isCorrectAnswer = rs.getInt("is_correct_answer");
			String updateAt = rs.getString("update_at");

			System.out.println(resultNo + "\t" + experimentId + "\t" + theme + "\t" + input + "\t" + isCorrectAnswer + "\t" + updateAt);
        }

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	    	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		try {
		tryConnect();
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT input, COUNT(input) AS num FROM results WHERE theme = '" + s + "' GROUP BY input");
		//結果のログ出力
		while (rs.next()) {
			String input = rs.getString("input");
			int num = rs.getInt("num");
			System.out.println(input + "\t" + num);
        }

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	    	try {
				tryClose();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}
