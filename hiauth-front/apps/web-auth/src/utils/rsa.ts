import JSEncrypt from 'jsencrypt';

const encryptor = new JSEncrypt();

export function encrypt(publicKey: string, content: string) {
  encryptor.setPublicKey(publicKey);
  return encryptor.encrypt(content);
}
