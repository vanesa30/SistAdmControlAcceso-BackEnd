package com.SistemaControlAcceso.app.auth;

public class JwtConfig {

public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEowIBAAKCAQEAxUbPmf9Yx/bAB+6WNAQlrChduT+yeYR6J7visbbYIyGYBygl\r\n"
			+ "DlsLDgRM9/h5McEbjM0gsfeR8PuYM2+aOiArLcuAE7S8Yv/n5iF5MOk6w13s/CPi\r\n"
			+ "sfL/J9QltufaFaBkKbzCb3Pr/s/orjDQVuulfQf0rXlBuYR+cXkSkK5T9HSXyL9+\r\n"
			+ "SQkpYPw+JZ8LSe91r/Q3q9ZjbLOFRGRpIOiyyuyiBE+B9spWipiAj80JLlHmDhec\r\n"
			+ "sdMDCFiB6YkVkcmcxj5QW2111lSuI8nsGfWKaJGGXm1SGJWrVz2efy0+CUfzvfTl\r\n"
			+ "uKNzl2WCbwzX/J+Ra70PX6tmQH0URPz+9hK+MQIDAQABAoIBAQCWIOdIGIyl0GAf\r\n"
			+ "KTBhcW6B704z9geKdojHyDIicDVXtu8NMz3QCPUGu7sfUpZYiM7AfjKNfQwxSiZv\r\n"
			+ "rlS+O1DgzucvF/HtnTYLahniYPt5Rfddgqm4JmaLFuWEpjjPEHppUN7fFmbYSbyX\r\n"
			+ "I0rJexc7aVyIz6h22B+plyvLsv7tHp3GWVbUDzITh//gFlkRVqamC26BOUUKHAZP\r\n"
			+ "A+25acLq0Na2hFs08d0HTZYs+2IF+FI/O5LsJMw4heZUej70XVJXGpw8yhSrwvQ9\r\n"
			+ "aZUd0qGivtsq/7R/1mL/b7IbPGkVEXpS9hLcVsFIjZTZIUnBaCYuFL7SHpU7ON7j\r\n"
			+ "4hBN5j4pAoGBAPh7eOdiMJssOwvPtTvnR5zKx7fxlHfthLtS8nkgqlVrU5J3kiiM\r\n"
			+ "fTZGZXpZwUF8s3kpUYOfbxXUwQuTLzlnwAK+dql9ar5L4l/Va5E7XhbdZtAWaf34\r\n"
			+ "wy2mFj7TZR/vHQKOZjt1dNDgFa+MEN66JQ17ciP0GS7EpzMfTYtGqwFLAoGBAMs+\r\n"
			+ "vmq7FRe2sx1pzrPINQNnEUViHat2JLICEHWS1SXsrDgiUXI1/b1+EzvJKH+xPLv2\r\n"
			+ "su5GXi0B5NouZK7jepBOcfyMnGO4hYtLNSUhdvuxWnWDwYGlQOLH64Fo/+Py4Jwu\r\n"
			+ "nv0du/fAdutWGEMJc8CWlbELRSTXh5oFefc7cwzzAoGAfnMfMOjYAW990OHvwb9N\r\n"
			+ "wy2yexo2St2VaVqpyBhmdje6/NNajsYHxkIGzD+2yCO0lZkAQXI2Lq7pWoGZS6ZP\r\n"
			+ "oVXZvyDy3Epztf8+0Vab6BjdhFGTuMoKUUWvFmOvl5OweKeMwP9h5hXTE1W9FAs4\r\n"
			+ "ZecHS+Khmho7WBm69YCN74cCgYBB2sOyK4orE9y3lBgQsrdSseursp3wd6huMTMK\r\n"
			+ "FZiQ4D3xNlVzj0D3TEmJHqXLsi7+rHHPuOc86BcyBsRC0pxc874FWb42pYxH1QeU\r\n"
			+ "T2KRLSclWpqxmk0K7kJFvTLktkbG9Gg5lY/ZN/iQSmY0GnTyGxjHs+IkjN14L3wU\r\n"
			+ "eqPNtwKBgFPwJT+lTttQ8hFDj9fD5CHP+RuvTwl1Qc1QKrpbH6tY/qLvLBjfQlsY\r\n"
			+ "cnLxd2Op7tzKSPqNHPz0Xc5qrxhS++ZJfI+j7cLasjkApZFKcDaCPWEwteRWMm/M\r\n"
			+ "xX/lE86yaNPSiPO/D0lfynQLdw6FFB3kOQtTvvAdLWWS90bRjPFc\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwRRkppjNSOZkENkh8/JN\r\n" + 
			"k7vjVq1J7jRWT89ZrgOYbS7BkKDXYYo3ylxi/Z7aQE5SYP1RIuz0Iw8ZuCsPr4Sh\r\n" + 
			"4nbkbWIPnthTB0ZWizmQCC2La81HU78zMCw0AetdGVU0+P0LAh+9lZAdXiZiTqFv\r\n" + 
			"iNbOj/pfJqyPz6r9/7/HUDVuxpeOKXQO7PMhsQrwCAkj7oxVdXyoKjByyx1vz19A\r\n" + 
			"9sZgKrWhthhg409ngy3/RRTtzXqfgg6RgUNIeKmhQjC4E+1K8JtG3dBCFpGuZaCc\r\n" + 
			"/Y+PO4aj7c2JaQ2BtQvw9ZmlQmIARlrD2wTfkld/gnsah9KPAUGzSK2Am2jiTTVn\r\n" + 
			"XwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";

}
