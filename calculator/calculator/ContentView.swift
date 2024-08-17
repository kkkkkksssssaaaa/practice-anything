//
//  ContentView.swift
//  calculator
//
//  Created by 승 on 8/16/24.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            HStack {
                Text("7")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text("8")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text("9")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text("X")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.orange)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
            }
            HStack {
                Text("4")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text("5")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text("6")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text("-")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.orange)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
            }
            HStack {
                Text("1")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text("2")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text("3")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text("+")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.orange)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
            }
            HStack {
                Text("0")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text("0")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text(".")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.gray)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
                Text("=")
                    .frame(
                        width: 80,
                        height: 80
                    ).background(.orange)
                    .cornerRadius(40)
                    .foregroundColor(.white)
                    .font(.system(size: 33))
            }
        }
    }
}

#Preview {
    ContentView()
}
