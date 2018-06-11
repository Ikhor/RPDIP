#include "JNIStarter.h"
#include <iostream>
#include <omp.h>
#include <vector>
#include <algorithm>
#include <math.h>
using namespace std;

void FilterBinary(jint* img, int len, int strouf, int nThread){

	#pragma omp omp_set_num_threads(nThread);
	int i;
	#pragma omp parallel for private(i)
	for( i = 0; i < len; i++ ){
		if(img[i] > strouf)
			img[i] = 255;
		else
			img[i] = 0;
	}	
}

void FilterNegative(jint* img, int len, int nThread){

	#pragma omp omp_set_num_threads(nThread);
	int i;
	#pragma omp parallel for private(i)
	for( i = 0; i < len; i++ ){
		img[i] = 255-img[i];	
	}	
}

void FilterGamma(jint* img, int len, float gamma, int c, int nThread){
	
	gamma = 1.01f;
	c = 0;
	if (gamma < 1) {
		c = (int) (256 / (pow(255, gamma)));
	} else {
		c = (int) ((pow(255, gamma)) / 255);
	}
	#pragma omp omp_set_num_threads(nThread);
	int i;
	#pragma omp parallel for private(i)
	for( i = 0; i < len; i++ ){
		img[i] =(jint) c * (pow(img[i], gamma));
	}	
}

void FilterLogarithmic(jint* img, int len, int c, int nThread){

	#pragma omp omp_set_num_threads(nThread);
	int i;
	#pragma omp parallel for private(i)
	for( i = 0; i < len; i++ ){
		img[i] = (jint) 30 * log(1 + img[i]);
	}	
}

void FilterGrayScale(jint* imgR,jint* imgG,jint* imgB, int len, int nThread){

	#pragma omp omp_set_num_threads(nThread);
	int i;
	#pragma omp parallel for private(i)
	for( i = 0; i < len; i++ ){
		imgR[i] = (0.299 * imgR[i] + 0.587 * imgG[i]  + 0.114 * imgB[i] );
		imgG[i] = (0.299 * imgR[i] + 0.587 * imgG[i]  + 0.114 * imgB[i] );
		imgB[i] = (0.299 * imgR[i] + 0.587 * imgG[i]  + 0.114 * imgB[i] );
	}	
}


void CoordToInt(int width,int linha, int coluna, int& valorInteiro){
	valorInteiro = width * linha + coluna;
}

int getMedian(jdouble *img, int width, int linha,int coluna){

	int linhaAtual, colunaAtual;
	vector<int> vizinhanca;

	#pragma omp parallel for private(linhaAtual)
	for(linhaAtual = linha - 1; linhaAtual <= linha + 1; linhaAtual++){
		for(colunaAtual=coluna-1; colunaAtual <= coluna+1; colunaAtual++){
			int posicao;
			CoordToInt(width, linhaAtual, colunaAtual, posicao);
			vizinhanca.push_back(img[posicao]);
		}
	}
	//barreira implicita aki
	sort(vizinhanca.begin(), vizinhanca.end());
	return vizinhanca[4];
}

int* Median(jdouble *img, int width, int height){
	
	int i, j,pos,median;

	int *imgReturn = new int[width*height]; 

	#pragma omp parallel for private(i)
	for(i = 1; i <= height - 1; i++){
		#pragma omp parallel for private(j)
		for(j = 1; j <= width - 1; j++){
			median = getMedian(img, width,i ,j);
			CoordToInt(width, i, j, pos);
			imgReturn[pos] = median;	
		}
	}
	return imgReturn;
}


//JNI EXPORT

JNIEXPORT void JNICALL Java_serverSide_Filters_JNI_JNIStarter_Negative(JNIEnv *env, jobject, jintArray img, jint nThread){
   
	
	jsize len = env->GetArrayLength(img);
	jint *imagem = env->GetIntArrayElements(img, 0);
	
	FilterNegative(imagem,len, nThread);
	
	env->ReleaseIntArrayElements(img, imagem, 0);

}  

JNIEXPORT void JNICALL Java_serverSide_Filters_JNI_JNIStarter_Binary(JNIEnv *env, jobject, jintArray img,jint Strouf, jint nThread){
   
	
	jsize len = env->GetArrayLength(img);
	jint *imagem = env->GetIntArrayElements(img, 0);
	
	FilterBinary(imagem,len,Strouf, nThread);
	
	env->ReleaseIntArrayElements(img, imagem, 0);

}

JNIEXPORT void JNICALL Java_serverSide_Filters_JNI_JNIStarter_Gamma(JNIEnv *env, jobject, jintArray img, jint gamma, jint c, jint nThread){
   
	
	jsize len = env->GetArrayLength(img);
	jint *imagem = env->GetIntArrayElements(img, 0);
	
	FilterGamma(imagem,len,gamma,c, nThread);
	
	env->ReleaseIntArrayElements(img, imagem, 0);

} 

JNIEXPORT void JNICALL Java_serverSide_Filters_JNI_JNIStarter_Logarithmic(JNIEnv *env, jobject, jintArray img, jint gamma, jint c, jint nThread){
   
	
	jsize len = env->GetArrayLength(img);
	jint *imagem = env->GetIntArrayElements(img, 0);

	FilterLogarithmic(imagem,len,c, nThread);
	
	env->ReleaseIntArrayElements(img, imagem, 0);

} 

JNIEXPORT void JNICALL Java_serverSide_Filters_JNI_JNIStarter_GrayScale(JNIEnv *env, jobject, jintArray imgR, jintArray imgG, jintArray imgB, jint nThread){
   
	
	jsize len = env->GetArrayLength(imgR);

	jint *imagemR = env->GetIntArrayElements(imgR, 0);
	jint *imagemG = env->GetIntArrayElements(imgG, 0);
	jint *imagemB = env->GetIntArrayElements(imgB, 0);
	
	FilterGrayScale(imagemR,imagemG,imagemB,len, nThread);
	
	env->ReleaseIntArrayElements(imgR, imagemR, 0);
	env->ReleaseIntArrayElements(imgG, imagemG, 0);
	env->ReleaseIntArrayElements(imgB, imagemB, 0);

}